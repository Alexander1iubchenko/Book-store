package service;

import dto.cart.AddToCartRequestDto;
import dto.cart.CartItemRequestUpdateDto;
import dto.cart.ShoppingCartDto;
import exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mapper.ShoppingCartMapper;
import model.Book;
import model.CartItem;
import model.ShoppingCart;
import model.User;
import org.springframework.stereotype.Service;
import repository.BookRepository;
import repository.CartItemRepository;
import repository.ShoppingCartRepository;
import repository.UserRepository;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId) {
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException("Can not find book by this id:"
                        + requestDto.getBookId()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Can not find user by this id:"
                        + userId));

        ShoppingCart shoppingCartFromDb = shoppingCartRepository.findShoppingCartByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCartFromDb);
        cartItemRepository.save(cartItem);
        shoppingCartFromDb.getCartItems().add(cartItem);

        return shoppingCartMapper.toDto(shoppingCartFromDb);
    }

    @Override
    public ShoppingCartDto getShoppingCartDto(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Can not find Shopping "
                        + "cart with this user id" + userId));
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartDto updateCartItem(
            Long userId, Long cartItemId, CartItemRequestUpdateDto requestUpdateDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Can not find Shopping "
                        + "cart with this user id" + userId));
        CartItem cartItem = cartItemRepository.findByIdAndShoppingCartId(cartItemId,
                shoppingCart.getId()).orElseThrow(
                    () -> new EntityNotFoundException("Can't find and update cart "
                        + "item by id: " + cartItemId)
        );
        cartItem.setQuantity(requestUpdateDto.getQuantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public void deleteCartItemById(Long userId, Long id) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Can not find Shopping "
                        + "cart with this user id" + userId));
        cartItemRepository.findByIdAndShoppingCartId(id, shoppingCart.getId()).orElseThrow(
                () -> new EntityNotFoundException("Can't find cart item by id: " + id)
        );
        cartItemRepository.deleteById(id);
    }
}
