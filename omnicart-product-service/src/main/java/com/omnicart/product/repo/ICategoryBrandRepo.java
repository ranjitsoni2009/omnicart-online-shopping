package com.omnicart.product.repo;

import com.omnicart.product.entity.CategoryBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Ranjit Soni on 25-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Repository
public interface ICategoryBrandRepo extends JpaRepository<CategoryBrand, Long> {

    Optional<CategoryBrand> findByCategoryIdAndBrandId(long categoryId, long brandId);
}
