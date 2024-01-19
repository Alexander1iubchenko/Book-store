package com.example.application.service.order;

import com.example.application.dto.order.OrderCreateRequestShippingAddressDto;
import com.example.application.dto.order.OrderItemResponseDto;
import com.example.application.dto.order.OrderResponseDto;
import com.example.application.dto.order.OrderUpdateRequestStatusDto;
import com.example.application.exception.EntityNotFoundException;
import com.example.application.mapper.OrderItemMapper;
import com.example.application.mapper.OrderMapper;
import com.example.application.model.Order;
import com.example.application.model.OrderItem;
import com.example.application.model.OrderStatus;
import com.example.application.model.ShoppingCart;
import com.example.application.model.User;
import com.example.application.repository.CartItemRepository;
import com.example.application.repository.OrderItemRepository;
import com.example.application.repository.OrderRepository;
import com.example.application.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public List<OrderResponseDto> getAllUsersOrders(User user, Pageable pageable) {
        return orderRepository.findAllByUserId(user.getId(), pageable).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderStatus(Long id, OrderUpdateRequestStatusDto requestDto) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can not find order by this id: " + id)
        );
        order.setOrderStatus(requestDto.getOrderStatus());
        orderRepository.save(order);
        return orderMapper.toDto(order);
    }

    @Override
    @Transactional
    public OrderResponseDto createOrder(User user,
                                        OrderCreateRequestShippingAddressDto requestDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(user.getId())
                .orElseThrow(
                    () -> new EntityNotFoundException("Can not find shopping cart by this user id: "
                        + user.getId()));
        if (shoppingCart.getCartItems().isEmpty()) {
            throw new EntityNotFoundException("Your shopping cart is empty now");
        }

        BigDecimal total = shoppingCart.getCartItems().stream()
                .map(cartItem -> cartItem.getBook().getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);

        Order savedOrder = orderRepository
                .save(newOrder(user, requestDto.getShippingAddress(), total));

        List<OrderItem> orderItems = shoppingCart.getCartItems().stream()
                .map(cartItem -> new OrderItem(savedOrder, cartItem.getBook(),
                        cartItem.getQuantity(), cartItem.getBook().getPrice()))
                .collect(Collectors.toList());

        orderItemRepository.saveAll(orderItems);
        savedOrder.setItemSet(new HashSet<>(orderItems));
        cartItemRepository.deleteAll(shoppingCart.getCartItems());

        return orderMapper.toDto(savedOrder);
    }

    @Override
    @Transactional
    public List<OrderItemResponseDto> getAllOrderItemsFromOrder(
            Long userId, Long orderId, Pageable pageable) {
        orderRepository.findOrderByIdAndUserId(orderId, userId).orElseThrow(
                () -> new EntityNotFoundException("Can not find order by this id: " + orderId)
        );
        return orderItemRepository.findAllByOrderId(orderId, pageable).stream()
                .map(orderItemMapper::toDto)
                .toList();
    }

    @Override
    public OrderItemResponseDto findOrderItemInOrder(Long userId, Long orderId, Long orderItemId) {
        OrderItem orderItem = orderItemRepository.findByIdAndOrderId(orderItemId, orderId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Can not find order "
                                + "item by that id: " + orderItemId)
                );
        return orderItemMapper.toDto(orderItem);
    }

    private Order newOrder(User user, String shippingAddress, BigDecimal total) {
        Order order = new Order();
        order.setTotal(total);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setShippingAddress(shippingAddress);
        return order;
    }
}
