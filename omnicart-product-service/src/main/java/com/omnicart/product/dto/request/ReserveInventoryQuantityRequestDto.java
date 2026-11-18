package com.omnicart.product.dto.request;

import lombok.*;

/**
 * Created by Ranjit Soni on 18-11-2026.
 * Author: ranjitsoni2009@gmail.com
 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReserveInventoryQuantityRequestDto {

    private long cartItemId;
    private long productVariantId;
    private long vendorId;
    private long reservedQuantity;
}
