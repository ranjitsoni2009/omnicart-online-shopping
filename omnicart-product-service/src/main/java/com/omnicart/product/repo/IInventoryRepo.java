package com.omnicart.product.repo;

import com.omnicart.product.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInventoryRepo extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByProductVariantIdAndVendorId(long productVariantId, long vendorId);

    Page<Inventory> findByAdminIdAndIsActiveTrue(long adminId, Pageable pageable);
}
