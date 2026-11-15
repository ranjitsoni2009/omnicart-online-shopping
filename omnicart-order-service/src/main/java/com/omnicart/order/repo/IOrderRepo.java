package com.omnicart.order.repo;

import com.omnicart.order.entity.Order;
import com.omnicart.order.entity.OrderStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderNumber(String orderNumber);

    Page<Order> findByCustomerId(long customerId, Pageable pageable);

    Page<Order> findByOrderStatus(OrderStatus status, Pageable pageable);
}
