package com.omnicart.order.dto.response;

import com.omnicart.order.entity.OrderStatus;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderStatusHistoryResponseDto {

    private final OrderStatus previousStatus;
    private final OrderStatus newStatus;
    private final long changedBy;
    private final String remarks;
    private final Instant createdAt;
}
