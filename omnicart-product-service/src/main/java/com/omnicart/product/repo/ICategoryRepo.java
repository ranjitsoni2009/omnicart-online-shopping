package com.omnicart.product.repo;

import com.omnicart.product.entity.Brand;
import com.omnicart.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICategoryRepo extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    Page<Category> findByIsActive(boolean b, Pageable pageable);
}
