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
public class CreateVendorRequestDto {

    @NotNull(message = "Business can't be null")
    private String businessName;

    @NotNull(message = "Registration Number can't be null")
    private String registrationNumber;

    @NotNull(message = "Contact Number can't be null")
    private String contactNumber;

    private String address;
}
