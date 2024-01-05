package service.order;

import dto.order.OrderCreateRequestShippingAddressDto;
import dto.order.OrderItemResponseDto;
import dto.order.OrderResponseDto;
import dto.order.OrderUpdateRequestStatusDto;
import java.util.List;
import model.User;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    List<OrderResponseDto> getAllUsersOrders(User user, Pageable pageable);

    OrderResponseDto updateOrderStatus(Long id, OrderUpdateRequestStatusDto requestDto);

    OrderResponseDto createOrder(User user, OrderCreateRequestShippingAddressDto requestDto);

    List<OrderItemResponseDto> getAllOrderItemsFromOrder(Long userId, Long orderId,
                                                         Pageable pageable);

    OrderItemResponseDto findOrderItemInOrder(Long userId, Long orderId, Long orderItemId);
}
