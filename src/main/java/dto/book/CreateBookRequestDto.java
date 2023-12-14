package dto.book;

import java.math.BigDecimal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotNull(message = "Title cannot be null.")
    private String title;
    @NotNull(message = "Title cannot be null.")
    private String author;
    @NotNull(message = "Title cannot be null.")
    private String isbn;
    @NotNull(message = "Title cannot be null.")
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
