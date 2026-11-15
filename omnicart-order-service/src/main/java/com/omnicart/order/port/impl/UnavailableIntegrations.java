package com.omnicart.order.port.impl;

import com.omnicart.order.entity.OrderStatus;
import com.omnicart.order.exception.OrderBusinessException;
import com.omnicart.order.port.CartPort;
import com.omnicart.order.port.InventoryPort;
import com.omnicart.order.port.NotificationPort;
import com.omnicart.order.port.PaymentPort;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

/** Safe placeholders: owning services must replace these ports with verified contracts. */
public final class UnavailableIntegrations {

    private UnavailableIntegrations() {
    }

    private static OrderBusinessException unavailable() {
        return new OrderBusinessException("INTEGRATION_UNAVAILABLE", "Required external service contract is not configured");
    }

    @Component
    public static class CartAdapter implements CartPort {
        @Override
        public List<CartItem> getItems(long customerId) {
            throw unavailable();
        }

        @Override
        public void clear(long customerId) {
            throw unavailable();
        }
    }

    @Component
    public static class InventoryAdapter implements InventoryPort {
        @Override
        public void reserve(long orderId, List<Reservation> items) {
            throw unavailable();
        }

        @Override
        public void release(long orderId, List<Reservation> items) {
            throw unavailable();
        }
    }

    @Component
    public static class PaymentAdapter implements PaymentPort {
        @Override
        public void initiatePayment(long id, String number, BigDecimal amount) {
            throw unavailable();
        }

        @Override
        public void requestRefund(long id, BigDecimal amount) {
            throw unavailable();
        }
    }

    @Component
    public static class NotificationAdapter implements NotificationPort {
        @Override
        public void notify(long customerId, long orderId, OrderStatus status) {
            throw unavailable();
        }
    }
}
