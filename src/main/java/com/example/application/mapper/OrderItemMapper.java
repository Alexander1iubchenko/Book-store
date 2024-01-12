package com.example.application.mapper;

import com.example.application.config.MapperConfig;
import com.example.application.dto.order.OrderItemResponseDto;
import com.example.application.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    OrderItemResponseDto toDto(OrderItem orderItem);
}

