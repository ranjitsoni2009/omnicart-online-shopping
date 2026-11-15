package com.omnicart.order.port;
 import java.math.BigDecimal;
 public interface PaymentPort {
     void initiatePayment(long orderId, String orderNumber, BigDecimal amount);
     void requestRefund(long orderId, BigDecimal amount);
}
