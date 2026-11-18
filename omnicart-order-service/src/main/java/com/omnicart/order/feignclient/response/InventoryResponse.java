package com.omnicart.order.feignclient.response;

import lombok.*;

/**
 * Created by Ranjit Soni on 15-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InventoryResponse {
    private long id;
    private long productVariantId;
    private long vendorId;
    private long quantity;
    private long reservedQuantity;
    private long availableQuantity;
    private boolean active;
}
