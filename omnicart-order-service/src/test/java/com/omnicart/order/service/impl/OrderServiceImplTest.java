package com.omnicart.order.service.impl;

import com.omnicart.order.dto.request.CancelOrderRequestDto;
import com.omnicart.order.dto.request.CreateOrderRequestDto;
import com.omnicart.order.dto.request.UpdateOrderStatusRequestDto;
import com.omnicart.order.entity.Order;
import com.omnicart.order.entity.OrderStatus;
import com.omnicart.order.entity.PaymentStatus;
import com.omnicart.order.exception.OrderBusinessException;
import com.omnicart.order.port.CartPort;
import com.omnicart.order.port.InventoryPort;
import com.omnicart.order.port.NotificationPort;
import com.omnicart.order.port.PaymentPort;
import com.omnicart.order.repo.IOrderRepo;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    IOrderRepo repo;

    @Mock
    CartPort cart;

    @Mock
    InventoryPort inventory;

    @Mock
    PaymentPort payment;

    @Mock
    NotificationPort notification;

    @InjectMocks
    OrderServiceImpl service;

    @AfterEach
    void clear() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void createRejectsEmptyCart() {
        auth();
        when(cart.getItems(1)).thenReturn(List.of());
        CreateOrderRequestDto r = addresses();
        assertThatThrownBy(() -> service.create(r))
                .isInstanceOf(OrderBusinessException.class)
                .hasMessage("Cart is empty");
    }

    @Test
    void rejectsInvalidTransition() {
        auth();
        Order o = order(OrderStatus.PENDING_PAYMENT);
        when(repo.findById(2L)).thenReturn(Optional.of(o));
        UpdateOrderStatusRequestDto r = new UpdateOrderStatusRequestDto();
        r.setStatus(OrderStatus.DELIVERED);
        assertThatThrownBy(() -> service.updateOrderStatus(2, r))
                .isInstanceOf(OrderBusinessException.class)
                .hasMessage("Invalid order status transition");
    }

    @Test
    void cancellationIsIdempotent() {
        auth();
        Order o = order(OrderStatus.CANCELLED);
        when(repo.findById(2L)).thenReturn(Optional.of(o));
        CancelOrderRequestDto r = new CancelOrderRequestDto();
        r.setReason("Changed mind");
        assertThat(service.cancelOrder(2, r).getOrderStatus()).isEqualTo(OrderStatus.CANCELLED);
        verifyNoInteractions(inventory, payment);
    }

    private void auth() {
        TestingAuthenticationToken a = new TestingAuthenticationToken("1", null);
        a.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(a);
    }

    private CreateOrderRequestDto addresses() {
        CreateOrderRequestDto r = new CreateOrderRequestDto();
        r.setShippingAddressSnapshot("shipping");
        r.setBillingAddressSnapshot("billing");
        return r;
    }

    private Order order(OrderStatus s) {
        Order o = new Order();
        o.setId(2);
        o.setCustomerId(1);
        o.setOrderStatus(s);
        o.setPaymentStatus(PaymentStatus.PENDING);
        o.setOrderNumber("ORD-1");
        o.setSubtotal(BigDecimal.ZERO);
        o.setDiscountAmount(BigDecimal.ZERO);
        o.setTaxAmount(BigDecimal.ZERO);
        o.setShippingCharges(BigDecimal.ZERO);
        o.setTotalAmount(BigDecimal.ZERO);
        o.setCreatedAt(java.time.Instant.now());
        o.setItems(new ArrayList<>());
        o.setStatusHistory(new ArrayList<>());
        return o;
    }
}
