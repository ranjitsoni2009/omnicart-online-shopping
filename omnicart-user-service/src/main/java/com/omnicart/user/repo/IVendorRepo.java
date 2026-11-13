package com.omnicart.user.repo;

import com.omnicart.user.entity.VendorDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IVendorRepo extends JpaRepository<VendorDetail, Long> {

    Optional<VendorDetail> findByBusinessName(String businessName);
}
