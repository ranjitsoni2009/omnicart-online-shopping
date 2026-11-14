package com.omnicart.product.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateInventoryRequestDto {

    @NotNull(message = "Product variant is required")
    private Long productVariantId;

    @NotNull(message = "Vendor is required")
    private Long vendorId;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    private Long quantity;
}
