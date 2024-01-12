package com.example.application.mapper;

import com.example.application.config.MapperConfig;
import com.example.application.dto.cart.ShoppingCartDto;
import com.example.application.model.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = CartItemMapper.class)
public interface ShoppingCartMapper {
    @Mapping(target = "userId", source = "user.id")
    ShoppingCartDto toDto(ShoppingCart shoppingCart);
}
