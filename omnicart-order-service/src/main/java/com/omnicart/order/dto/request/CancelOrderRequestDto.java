package com.omnicart.order.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelOrderRequestDto {
    @NotBlank
    private String reason;
}
