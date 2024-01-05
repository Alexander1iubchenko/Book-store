package mapper;

import config.MapperConfig;
import dto.order.OrderResponseDto;
import model.Order;
import model.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "status", source = "status", qualifiedByName = "toString")
    @Mapping(target = "orderItems", source = "itemSet")
    OrderResponseDto toDto(Order order);

    @Named("toString")
    default String toString(Status status) {
        return status.toString();
    }
}
