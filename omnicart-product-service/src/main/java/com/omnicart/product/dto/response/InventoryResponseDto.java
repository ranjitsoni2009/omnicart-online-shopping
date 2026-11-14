package com.omnicart.product.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InventoryResponseDto {
    private final long id;
    private final long productVariantId;
    private final long vendorId;
    private final long quantity;
    private final long reservedQuantity;
    private final long availableQuantity;
    private final boolean active;
}
