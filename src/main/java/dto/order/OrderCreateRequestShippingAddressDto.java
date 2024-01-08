package dto.order;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderCreateRequestShippingAddressDto {
    @NotBlank
    @Size(min = 4, max = 255)
    private String shippingAddress;
}
