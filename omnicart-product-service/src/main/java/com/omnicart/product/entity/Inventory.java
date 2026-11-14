package com.omnicart.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "inventories",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_inventory_variant_vendor",
                columnNames = {"product_variant_id", "vendor_id"}))
public class Inventory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_variant_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventory_product_variant"))
    private ProductVariant productVariant;

    @Column(name = "vendor_id", nullable = false)
    private long vendorId;

    @Column(name = "admin_id", nullable = false, updatable = false)
    private long adminId;

    @Column(name = "quantity", nullable = false)
    private long quantity;

    @Column(name = "reserved_quantity", nullable = false)
    private long reservedQuantity;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;
}
