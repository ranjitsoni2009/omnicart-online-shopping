package com.omnicart.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * Created by Ranjit Soni on 13-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Getter
@Setter
@Entity
@Table(name = "addresses")
public class Address extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "is_deliverable")
    private boolean isDeliverable;

    @Column(name = "floor")
    private String floor;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "street_apartment")
    private String streetApartment;

    @Column(name = "society_block")
    private String societyBlock;

    @Column(name = "area")
    private String area;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pin_code")
    private String pinCode;

    @Column(name = "country")
    private String country;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
