package com.omnicart.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "order_status_history",
        indexes = @Index(name = "idx_order_status_history_order", columnList = "order_id"))
public class OrderStatusHistory extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 32)
    private OrderStatus orderStatus;

    @Column(name = "created_by", nullable = false)
    private long createdBy;

    @Column(name = "remarks", length = 255)
    private String remarks;
}
