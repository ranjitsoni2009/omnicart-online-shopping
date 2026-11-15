package com.omnicart.order.service.impl;

import com.omnicart.order.dto.request.CancelOrderRequestDto;
import com.omnicart.order.dto.request.CreateOrderRequestDto;
import com.omnicart.order.dto.request.UpdateOrderStatusRequestDto;
import com.omnicart.order.dto.response.OrderResponseDto;
import com.omnicart.order.dto.response.OrderStatusHistoryResponseDto;
import com.omnicart.order.entity.*;
import com.omnicart.order.exception.OrderBusinessException;
import com.omnicart.order.port.InventoryPort;
import com.omnicart.order.port.NotificationPort;
import com.omnicart.order.port.PaymentPort;
import com.omnicart.order.repo.ICartItemRepo;
import com.omnicart.order.repo.IOrderRepo;
import com.omnicart.order.service.IOrderService;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepo orderRepo;
    private final ICartItemRepo cartItemRepo;
    private final InventoryPort inventory;
    private final PaymentPort payment;
    private final NotificationPort notification;

    @Transactional
    public OrderResponseDto create(CreateOrderRequestDto createOrderRequestDto) {
        long customerId = getCustomerId();
        List<CartItem> cartItems = cartItemRepo.findByCustomerId(customerId);

        if (cartItems.isEmpty()) {
            throw error("EMPTY_CART", "Cart is empty");
        }

        Order order = new Order();
        Instant now = Instant.now();
        order.setOrderNumber("ORD-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase());
        order.setCustomerId(customerId);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setShippingAddressSnapshot(createOrderRequestDto.getShippingAddressSnapshot());
        order.setBillingAddressSnapshot(createOrderRequestDto.getBillingAddressSnapshot());
        order.setCreatedAt(now);
        order.setUpdatedAt(now);

        OrderItemProcessResult orderItemProcessResult = getOrderitemProcessResult(cartItems, now, order);

        order.setSubtotal(orderItemProcessResult.subTotal());
        order.setDiscountAmount(orderItemProcessResult.totalDiscount());
        order.setTaxAmount(orderItemProcessResult.totalTax());
        order.setShippingCharges(BigDecimal.ZERO);
        order.setTotalAmount(orderItemProcessResult.subTotal().subtract(orderItemProcessResult.totalDiscount()).add(orderItemProcessResult.totalTax()));

        transition(order, null, OrderStatus.PENDING_PAYMENT, customerId, "Order created");
        order = orderRepo.save(order);

        List<InventoryPort.Reservation> reserve = cartItems.stream()
                .map(i -> new InventoryPort.Reservation(i.getProductVariantId(), i.getQuantity()))
                .toList();
        inventory.reserve(order.getId(), reserve);
        payment.initiatePayment(order.getId(), order.getOrderNumber(), order.getTotalAmount());
        notification.notify(customerId, order.getId(), order.getOrderStatus());
        return dto(order);
    }

    private OrderItemProcessResult getOrderitemProcessResult(List<CartItem> cartItems, Instant now, Order order) {
        BigDecimal subTotal = BigDecimal.ZERO;
        BigDecimal totalDiscount = BigDecimal.ZERO;
        BigDecimal totalTax = BigDecimal.ZERO;

        for (var cartItem : cartItems) {
            if (cartItem.getQuantity() <= 0 || cartItem.getUnitPrice() == null) {
                throw error("INVALID_CART", "Cart contains invalid item");
            }

            BigDecimal discountPercentage = zero(cartItem.getDiscountPercentage());
            BigDecimal taxPercentage = zero(cartItem.getTaxPercentage());
            BigDecimal baseAmount = cartItem.getUnitPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity()));;

            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setVendorId(cartItem.getVendorId());
            orderItem.setProductName(cartItem.getProductName());
            orderItem.setSku(cartItem.getSku());
            orderItem.setUnitPrice(cartItem.getUnitPrice());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscountPercentage(discountPercentage);
            orderItem.setTaxPercentage(taxPercentage);

            BigDecimal discount = baseAmount.multiply(discountPercentage);
            BigDecimal discountedAmount = baseAmount.subtract(discount);
            BigDecimal tax = discountedAmount.multiply(taxPercentage);
            orderItem.setLineTotal(discountedAmount.add(tax));
            orderItem.setCreatedAt(now);
            orderItem.setUpdatedAt(now);
            order.addItem(orderItem);

            subTotal = subTotal.add(baseAmount);
            totalDiscount = totalDiscount.add(discount);
            totalTax = totalTax.add(tax);
        }
        return new OrderItemProcessResult(subTotal, totalDiscount, totalTax);
    }

    private record OrderItemProcessResult(BigDecimal subTotal, BigDecimal totalDiscount, BigDecimal totalTax) { }

    @Transactional(readOnly = true)
    public OrderResponseDto getMyOrder(long id) {
        Order o = found(id);
        own(o);
        return dto(o);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrderById(long id) {
        return dto(found(id));
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrderByOrderNumber(String n) {
        return dto(orderRepo.findByOrderNumber(n).orElseThrow(() -> error("ORDER_NOT_FOUND", "Order not found")));
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getMyOrders(int p, int s, String sort) {
        return orderRepo.findByCustomerId(getCustomerId(), page(p, s, sort)).map(this::dto);
    }

    @Transactional(readOnly = true)
    public Page<OrderResponseDto> getAllOrder(OrderStatus st, int p, int s, String sort) {
        return st == null
                ? orderRepo.findAll(page(p, s, sort)).map(this::dto)
                : orderRepo.findByOrderStatus(st, page(p, s, sort)).map(this::dto);
    }

    @Transactional(readOnly = true)
    public List<OrderStatusHistoryResponseDto> getOrderStatusHistory(long id) {
        Order o = found(id);
        own(o);
        return o.getStatusHistory().stream()
                .map(h -> OrderStatusHistoryResponseDto.builder()
                        .previousStatus(h.getPreviousStatus())
                        .newStatus(h.getNewStatus())
                        .changedBy(h.getChangedBy())
                        .remarks(h.getRemarks())
                        .createdAt(h.getCreatedAt())
                        .build())
                .toList();
    }

    @Transactional
    public OrderResponseDto cancelOrder(long id, CancelOrderRequestDto r) {
        Order o = found(id);
        own(o);

        if (o.getOrderStatus() == OrderStatus.CANCELLED
                || o.getOrderStatus() == OrderStatus.REFUND_INITIATED
                || o.getOrderStatus() == OrderStatus.REFUNDED) {
            return dto(o);
        }

        if (!canCancel(o.getOrderStatus())) {
            throw error("CANCELLATION_NOT_ALLOWED", "Order cannot be cancelled in its current status");
        }

        OrderStatus prior = o.getOrderStatus();
        transition(o, prior, OrderStatus.CANCELLED, getCustomerId(), r.getReason());
        o.setCancellationReason(r.getReason());
        o.setCancelledAt(Instant.now());

        if (o.getPaymentStatus() == PaymentStatus.SUCCESS) {
            o.setPaymentStatus(PaymentStatus.REFUND_PENDING);
            payment.requestRefund(o.getId(), o.getTotalAmount());
            transition(o, OrderStatus.CANCELLED, OrderStatus.REFUND_INITIATED, getCustomerId(), "Refund requested");
        }

        inventory.release(o.getId(), reservations(o));
        return dto(orderRepo.save(o));
    }

    @Transactional
    public OrderResponseDto updateOrderStatus(long id, UpdateOrderStatusRequestDto r) {
        Order o = found(id);

        if (!valid(o.getOrderStatus(), r.getStatus())) {
            throw error("INVALID_ORDER_STATUS", "Invalid order status transition");
        }

        transition(o, o.getOrderStatus(), r.getStatus(), getCustomerId(), r.getRemarks());

        if (r.getStatus() == OrderStatus.PAYMENT_SUCCESS) {
            o.setPaymentStatus(PaymentStatus.SUCCESS);
        }

        if (r.getStatus() == OrderStatus.PAYMENT_FAILED) {
            o.setPaymentStatus(PaymentStatus.FAILED);
            inventory.release(o.getId(), reservations(o));
        }

        if (r.getStatus() == OrderStatus.REFUNDED) {
            o.setPaymentStatus(PaymentStatus.REFUNDED);
        }

        notification.notify(o.getCustomerId(), o.getId(), r.getStatus());
        return dto(orderRepo.save(o));
    }

    private void transition(Order order, OrderStatus before, OrderStatus after, long who, String remark) {
        order.setOrderStatus(after);
        order.setUpdatedAt(Instant.now());

        OrderStatusHistory h = new OrderStatusHistory();
        h.setPreviousStatus(before);
        h.setNewStatus(after);
        h.setChangedBy(who);
        h.setRemarks(remark);
        h.setCreatedAt(Instant.now());
        h.setUpdatedAt(Instant.now());
        order.addHistory(h);
    }

    private boolean valid(OrderStatus a, OrderStatus b) {
        return switch (a) {
            case PENDING_PAYMENT -> b == OrderStatus.PAYMENT_SUCCESS
                    || b == OrderStatus.PAYMENT_FAILED
                    || b == OrderStatus.CANCELLED;
            case PAYMENT_SUCCESS -> b == OrderStatus.CONFIRMED;
            case CONFIRMED -> b == OrderStatus.PROCESSING || b == OrderStatus.CANCELLED;
            case PROCESSING -> b == OrderStatus.READY_FOR_SHIPMENT || b == OrderStatus.CANCELLED;
            case READY_FOR_SHIPMENT -> b == OrderStatus.SHIPPED;
            case SHIPPED -> b == OrderStatus.OUT_FOR_DELIVERY;
            case OUT_FOR_DELIVERY -> b == OrderStatus.DELIVERED;
            case REFUND_INITIATED -> b == OrderStatus.REFUNDED;
            default -> false;
        };
    }

    private boolean canCancel(OrderStatus s) {
        return s == OrderStatus.PENDING_PAYMENT
                || s == OrderStatus.PAYMENT_SUCCESS
                || s == OrderStatus.CONFIRMED
                || s == OrderStatus.PROCESSING;
    }

    private List<InventoryPort.Reservation> reservations(Order o) {
        return o.getItems().stream()
                .map(i -> new InventoryPort.Reservation(i.getProductId(), i.getQuantity()))
                .toList();
    }

    private Order found(long id) {
        return orderRepo.findById(id).orElseThrow(() -> error("ORDER_NOT_FOUND", "Order not found"));
    }

    private void own(Order o) {
        if (o.getCustomerId() != getCustomerId()) {
            throw error("FORBIDDEN", "You cannot access another customer's order");
        }
    }

    private long getCustomerId() {
        try {
            return Long.parseLong(SecurityContextHolder.getContext().getAuthentication().getName());
        } catch (Exception e) {
            throw error("FORBIDDEN", "Authenticated user required");
        }
    }

    private Pageable page(int p, int s, String sort) {
        return PageRequest.of(p, s, Sort.by(Sort.Direction.DESC, sort == null ? "createdAt" : sort));
    }

    private BigDecimal zero(BigDecimal x) {
        return x == null ? BigDecimal.ZERO : x;
    }

    private OrderBusinessException error(String c, String m) {
        return new OrderBusinessException(c, m);
    }

    private OrderResponseDto dto(Order o) {
        return OrderResponseDto.builder()
                .id(o.getId())
                .orderNumber(o.getOrderNumber())
                .orderStatus(o.getOrderStatus())
                .paymentStatus(o.getPaymentStatus())
                .subtotal(o.getSubtotal())
                .discountAmount(o.getDiscountAmount())
                .taxAmount(o.getTaxAmount())
                .shippingCharges(o.getShippingCharges())
                .totalAmount(o.getTotalAmount())
                .createdAt(o.getCreatedAt())
                .items(o.getItems().stream()
                        .map(i -> OrderResponseDto.Item.builder()
                                .productId(i.getProductId())
                                .vendorId(i.getVendorId())
                                .productName(i.getProductName())
                                .sku(i.getSku())
                                .unitPrice(i.getUnitPrice())
                                .quantity(i.getQuantity())
                                .lineTotal(i.getLineTotal())
                                .build())
                        .toList())
                .build();
    }
}
