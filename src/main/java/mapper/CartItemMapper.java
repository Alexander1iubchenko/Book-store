package mapper;

import config.MapperConfig;
import dto.cart.CartItemDto;
import model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "bookTitle", source = "book.title")
    CartItemDto toDto(CartItem cartItem);
}
