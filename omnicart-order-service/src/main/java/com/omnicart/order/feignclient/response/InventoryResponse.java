package com.omnicart.order.feignclient.response;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by Ranjit Soni on 15-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Builder
public class InventoryResponse {
    private final long id;
    private final long productVariantId;
    private final long vendorId;
    private final long quantity;
    private final long reservedQuantity;
    private final long availableQuantity;
    private final boolean active;
}
