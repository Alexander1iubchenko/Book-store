package com.example.application.service.order;

import com.example.application.dto.order.OrderCreateRequestShippingAddressDto;
import com.example.application.dto.order.OrderItemResponseDto;
import com.example.application.dto.order.OrderResponseDto;
import com.example.application.dto.order.OrderUpdateRequestStatusDto;
import com.example.application.model.User;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderResponseDto> getAllUsersOrders(User user, Pageable pageable);

    OrderResponseDto updateOrderStatus(Long id, OrderUpdateRequestStatusDto requestDto);

    OrderResponseDto createOrder(User user, OrderCreateRequestShippingAddressDto requestDto);

    List<OrderItemResponseDto> getAllOrderItemsFromOrder(Long userId, Long orderId,
                                                         Pageable pageable);

    OrderItemResponseDto findOrderItemInOrder(Long userId, Long orderId, Long orderItemId);
}
