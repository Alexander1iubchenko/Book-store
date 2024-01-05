package service.order;

import dto.order.OrderCreateRequestShippingAddressDto;
import dto.order.OrderItemResponseDto;
import dto.order.OrderResponseDto;
import dto.order.OrderUpdateRequestStatusDto;
import exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mapper.OrderItemMapper;
import mapper.OrderMapper;
import model.Order;
import model.OrderItem;
import model.ShoppingCart;
import model.Status;
import model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.CartItemRepository;
import repository.OrderItemRepository;
import repository.OrderRepository;
import repository.ShoppingCartRepository;

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
        order.setStatus(requestDto.getStatus());
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
    @Transactional
    public OrderItemResponseDto findOrderItemInOrder(Long userId, Long orderId, Long orderItemId) {
        Order order = orderRepository.findOrderByIdAndUserId(orderId, userId).orElseThrow(
                () -> new EntityNotFoundException("Can not find order by this id: " + orderId)
        );
        return orderItemMapper.toDto(order.getItemSet().stream()
                .filter(orderItem -> orderItem.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(
                        () -> new EntityNotFoundException("Can't find orderItem by id: "
                                + orderItemId)));
    }

    private Order newOrder(User user, String shippingAddress, BigDecimal total) {
        Order order = new Order();
        order.setTotal(total);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.PENDING);
        order.setShippingAddress(shippingAddress);
        return order;
    }
}
