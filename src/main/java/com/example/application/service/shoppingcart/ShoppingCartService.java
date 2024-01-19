package com.example.application.service.shoppingcart;

import com.example.application.dto.cart.AddToCartRequestDto;
import com.example.application.dto.cart.CartItemRequestUpdateDto;
import com.example.application.dto.cart.ShoppingCartDto;

public interface ShoppingCartService {
    ShoppingCartDto addToCart(AddToCartRequestDto requestDto, Long userId);

    ShoppingCartDto getShoppingCartDto(Long userId);

    ShoppingCartDto updateCartItem(Long userId, Long cartItemId,
                                                        CartItemRequestUpdateDto requestUpdateDto);

    void deleteCartItemById(Long userId, Long id);
}
