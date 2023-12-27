package mapper;

import config.MapperConfig;
import dto.category.CategoryDto;
import dto.category.CategoryRequestDto;
import model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryRequestDto categoryRequestDto);
}
