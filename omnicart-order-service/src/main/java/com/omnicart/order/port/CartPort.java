package com.omnicart.order.port;
import java.math.BigDecimal;
import java.util.List;

public interface CartPort {
     List<CartItem> getItems(long customerId);
     void clear(long customerId);

     record CartItem(
             long productId,
             long vendorId,
             long productVariantId,
             String productName,
             String sku,
             int quantity,
             BigDecimal unitPrice,
             BigDecimal discount,
             BigDecimal tax) { }
}
