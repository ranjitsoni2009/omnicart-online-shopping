package com.omnicart.order.repo;

import com.omnicart.order.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICartItemRepo extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCustomerId(long customerId);

    Optional<CartItem> findByProductVariantIdAndVendorId(long productVariantId, long vendorId);

    void deleteAllByCustomerId(long customerId);
}
