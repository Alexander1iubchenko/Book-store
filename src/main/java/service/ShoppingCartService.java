package service;

import dto.cart.AddToCartRequestDto;
import dto.cart.CartItemRequestUpdateDto;
import dto.cart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId);

    ShoppingCartDto getShoppingCartDto(Long userId);

    ShoppingCartDto updateQuantityOfBooksInShoppingCart(Long userId, Long id,
                                                        CartItemRequestUpdateDto requestUpdateDto);

    void deleteCartItemById(Long userId, Long id);
}
