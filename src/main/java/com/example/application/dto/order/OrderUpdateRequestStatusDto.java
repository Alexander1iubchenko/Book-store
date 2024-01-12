package com.example.application.dto.order;

import com.example.application.model.OrderStatus;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderUpdateRequestStatusDto {
    @NotNull
    private OrderStatus orderStatus;
}
