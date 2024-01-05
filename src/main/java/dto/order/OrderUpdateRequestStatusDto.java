package dto.order;

import javax.validation.constraints.NotNull;
import lombok.Data;
import model.Status;

@Data
public class OrderUpdateRequestStatusDto {
    @NotNull
    private Status status;
}
