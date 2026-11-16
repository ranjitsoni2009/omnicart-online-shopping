package com.omnicart.order.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "orders",
        indexes = {
            @Index(name = "idx_orders_customer_created", columnList = "customer_id,created_at"),
            @Index(name = "idx_orders_status", columnList = "order_status")
        })
public class Order extends BaseEntity {
    @Column(name = "order_number", nullable = false, unique = true, updatable = false, length = 40)
    private String orderNumber;

    @Column(name = "customer_id", nullable = false)
    private long customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false, length = 32)
    private OrderStatus orderStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false, length = 24)
    private PaymentStatus paymentStatus;

    @Column(name = "sub_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal subtotal;

    @Column(name = "discounted_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal discountedAmount;

    @Column(name = "tax_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal taxAmount;

    @Column(name = "shipping_charges", nullable = false, precision = 19, scale = 2)
    private BigDecimal shippingCharges;

    @Column(name = "total_amount", nullable = false, precision = 19, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "shipping_address_snapshot", nullable = false, length = 4000)
    private String shippingAddressSnapshot;

    @Column(name = "billing_address_snapshot", nullable = false, length = 4000)
    private String billingAddressSnapshot;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdAt ASC")
    private List<OrderStatusHistory> statusHistory = new ArrayList<>();

    public void addItem(OrderItem item) {
        item.setOrder(this);
        items.add(item);
    }

    public void addHistory(OrderStatusHistory history) {
        history.setOrder(this);
        statusHistory.add(history);
    }
}
