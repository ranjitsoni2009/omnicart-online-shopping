package com.omnicart.order.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequestDto {
    @NotBlank
    private String shippingAddressSnapshot;

    @NotBlank
    private String billingAddressSnapshot;
}
