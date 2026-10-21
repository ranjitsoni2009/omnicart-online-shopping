package com.omnicart.product.repo;

import com.omnicart.product.entity.Brand;
import com.omnicart.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {
}
