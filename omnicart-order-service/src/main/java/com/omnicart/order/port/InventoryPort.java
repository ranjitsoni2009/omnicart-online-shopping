package com.omnicart.order.port;
import java.util.List;
 public interface InventoryPort {
     void reserve(long orderId, List<Reservation> items);
     void release(long orderId, List<Reservation> items);
     record Reservation(long productVariantId, int quantity) {
    }
}
