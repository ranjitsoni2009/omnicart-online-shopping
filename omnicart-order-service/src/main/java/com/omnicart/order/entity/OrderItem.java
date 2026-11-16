package com.omnicart.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order_items", indexes = @Index(name = "idx_order_items_order", columnList = "order_id"))
public class OrderItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "product_variant_id", nullable = false)
    private long productVariantId;

    @Column(name = "vendor_id", nullable = false)
    private long vendorId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private int quantity;

    @Column(name= "discount_percentage", nullable = false, precision = 19, scale = 2)
    private BigDecimal discountPercentage;

    @Column(name = "tax_percentage", nullable = false, precision = 19, scale = 2)
    private BigDecimal taxPercentage;

    @Column(name = "line_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal lineTotal;
}
