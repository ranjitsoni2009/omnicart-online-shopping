package com.omnicart.product.repo;

import com.omnicart.product.dto.request.CreateProductRequestDto;
import com.omnicart.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByNameAndCategoryIdAndBrandId(String name, long categoryId, long brandId);
}
