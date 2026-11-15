package com.omnicart.order.entity;

public enum OrderStatus {
        CREATED,
        PENDING_PAYMENT,
        PAYMENT_SUCCESS,
        PAYMENT_FAILED,
        CONFIRMED,
        PROCESSING,
        READY_FOR_SHIPMENT,
        SHIPPED,
        OUT_FOR_DELIVERY,
        DELIVERED,
        CANCELLATION_REQUESTED,
        CANCELLED,
        REFUND_INITIATED,
        REFUNDED
}
