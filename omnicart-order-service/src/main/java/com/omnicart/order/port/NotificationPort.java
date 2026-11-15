package com.omnicart.order.port;
 import com.omnicart.order.entity.OrderStatus;
 public interface NotificationPort {
     void notify(long customerId, long orderId, OrderStatus status);
}
