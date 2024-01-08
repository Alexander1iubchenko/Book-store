package dto.order;

import javax.validation.constraints.NotNull;
import lombok.Data;
import model.OrderStatus;

@Data
public class OrderUpdateRequestStatusDto {
    @NotNull
    private OrderStatus orderStatus;
}
