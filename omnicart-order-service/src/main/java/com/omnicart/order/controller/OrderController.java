package com.omnicart.order.controller;
import com.omnicart.order.dto.request.*;
import com.omnicart.order.dto.response.*;
import com.omnicart.order.entity.OrderStatus;
import com.omnicart.order.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

   private final IOrderService service;

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody CreateOrderRequestDto requestDto) {
         return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestDto));
    }

    @GetMapping("/my-orders")
    @PreAuthorize("hasAuthority('READ_OWN_ORDERS')")
    public Page<OrderResponseDto> getMyOrders(
            @RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size, @RequestParam(defaultValue="createdAt") String sort) {
       return service.getMyOrders(page,size,sort);
    }

    @GetMapping("/my-orders/{id}")
    @PreAuthorize("hasAuthority('READ_OWN_ORDERS')")
    public OrderResponseDto getMyOrder(@PathVariable long id) {
       return service.getMyOrder(id);
    }

    @GetMapping("/{id}/status-history")
    @PreAuthorize("hasAuthority('READ_OWN_ORDERS')")
    public List<OrderStatusHistoryResponseDto> getOrderStatusHistory(@PathVariable long id) {
       return service.getOrderStatusHistory(id);
    }
    @PostMapping("/{id}/cancel")
    @PreAuthorize("hasAuthority('CANCEL_OWN_ORDER')")
    public OrderResponseDto cancelOrder(@PathVariable long id, @Valid @RequestBody CancelOrderRequestDto r) {
       return service.cancelOrder(id,r);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_ALL_ORDERS')")
    public OrderResponseDto getOrderById(@PathVariable long id) {
       return service.getOrderById(id);
    }

    @GetMapping("/number/{number}")
    @PreAuthorize("hasAuthority('READ_ALL_ORDERS')")
    public OrderResponseDto getOrderByOrderNumber(@PathVariable String number) {
       return service.getOrderByOrderNumber(number);
    }

    @GetMapping("/admin/orders")
    @PreAuthorize("hasAuthority('READ_ALL_ORDERS')")
    public Page<OrderResponseDto> getAllOrder(
            @RequestParam(required=false) OrderStatus status, @RequestParam(defaultValue="0") int page,
            @RequestParam(defaultValue="20")int size, @RequestParam(defaultValue="createdAt")String sort) {
       return service.getAllOrder(status,page,size,sort);
    }

    @PatchMapping("/admin/orders/{id}/status")
    @PreAuthorize("hasAuthority('UPDATE_ORDER_STATUS')")
    public OrderResponseDto updateOrderStatus(@PathVariable long id,@Valid @RequestBody UpdateOrderStatusRequestDto r) {
       return service.updateOrderStatus(id,r);
    }
}
