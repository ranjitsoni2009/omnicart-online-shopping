package com.omnicart.order.service;
import com.omnicart.order.dto.request.*;
 import com.omnicart.order.dto.response.*;
 import com.omnicart.order.entity.OrderStatus;
 import org.springframework.data.domain.Page;
 import java.util.List;

public interface IOrderService {
    OrderResponseDto create(CreateOrderRequestDto createOrderRequestDto);
    OrderResponseDto getMyOrder(long id);
    OrderResponseDto getOrderById(long id);
    OrderResponseDto getOrderByOrderNumber(String number);
    Page<OrderResponseDto> getMyOrders(int page,int size,String sort);
    Page<OrderResponseDto> getAllOrder(OrderStatus status,int page,int size,String sort);
    List<OrderStatusHistoryResponseDto> getOrderStatusHistory(long id);
    OrderResponseDto cancelOrder(long id,CancelOrderRequestDto request);
    OrderResponseDto updateOrderStatus(long id,UpdateOrderStatusRequestDto request);
}
