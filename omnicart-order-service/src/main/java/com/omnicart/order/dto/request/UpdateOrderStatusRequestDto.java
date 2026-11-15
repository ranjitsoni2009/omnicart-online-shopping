package com.omnicart.order.dto.request;
import com.omnicart.order.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequestDto {

    @NotNull
    private OrderStatus status;
    private String remarks;
}
