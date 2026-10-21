package com.omnicart.product.repo;

import com.omnicart.product.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IBrandRepo extends JpaRepository<Brand, Long> {

    Optional<Brand> findByName(String brandName);
}
