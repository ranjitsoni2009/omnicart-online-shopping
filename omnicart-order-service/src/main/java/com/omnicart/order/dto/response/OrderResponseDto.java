package com.omnicart.order.dto.response;
import com.omnicart.order.entity.OrderStatus;
 import com.omnicart.order.entity.PaymentStatus;
 import lombok.Builder;
 import lombok.Getter;
import java.math.BigDecimal;
 import java.time.Instant;
 import java.util.List;

@Getter
@Builder
public class OrderResponseDto {
    private final long id;
    private final String orderNumber;
    private final OrderStatus orderStatus;
    private final PaymentStatus paymentStatus;
    private final BigDecimal subtotal;
    private final BigDecimal discountAmount;
    private final BigDecimal taxAmount;
    private final BigDecimal shippingCharges;
    private final BigDecimal totalAmount;
    private final Instant createdAt;
    private final List<Item> items;

    @Getter
    @Builder
    public static class Item {
        private final long productId;
        private final long vendorId;
        private final String productName;
        private final String sku;
        private final BigDecimal unitPrice;
        private final int quantity;
        private final BigDecimal lineTotal;
    }
}
