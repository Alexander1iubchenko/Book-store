package dto.order;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCreateRequestShippingAddressDto {
    @NotNull
    private String shippingAddress;
}
