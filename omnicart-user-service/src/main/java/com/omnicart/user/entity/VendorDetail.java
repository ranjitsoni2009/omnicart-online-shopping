package com.omnicart.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 28-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Entity
@Table(name = "vendor_details")
public class VendorDetail extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;
}
