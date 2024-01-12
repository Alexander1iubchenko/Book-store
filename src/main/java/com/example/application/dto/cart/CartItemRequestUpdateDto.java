package com.example.application.dto.cart;

import javax.validation.constraints.Positive;
import lombok.Data;

@Data
public class CartItemRequestUpdateDto {
    @Positive
    private Integer quantity;
}
