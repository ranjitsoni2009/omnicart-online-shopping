package com.omnicart.product.repo;

import com.omnicart.product.entity.Brand;
import com.omnicart.product.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductVariantRepo extends JpaRepository<ProductVariant, Long> {
}
