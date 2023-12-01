package dto;

import javax.validation.constraints.Min;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NonNull;

@Data
public class CreateBookRequestDto {
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private String author;
    @NonNull
    private String isbn;
    @NonNull
    @Min(0)
    private BigDecimal price;
    private String description;
    private String coverImage;
}
