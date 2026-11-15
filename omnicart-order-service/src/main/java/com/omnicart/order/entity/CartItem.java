package com.omnicart.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Created by Ranjit Soni on 15-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "product_id")
    private long productId;

    @Column(name = "product_variant_id")
    private long productVariantId;

    @Column(name = "vendor_id")
    private long vendorId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "sku")
    private String sku;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "discount_percentage")
    private BigDecimal discountPercentage;

    @Column(name = "tax_percentage")
    private BigDecimal taxPercentage;
}
