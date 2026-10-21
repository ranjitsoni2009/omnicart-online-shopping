package com.omnicart.product.repo;

import com.omnicart.product.entity.Brand;
import com.omnicart.product.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Long> {
}
