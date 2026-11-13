package com.omnicart.user.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Ranjit Soni on 28-10-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
public class UpdateVendorRequestDto {

    private long id;
    private String businessName;
    private String registrationNumber;
    private String contactNumber;
    private String address;
}
