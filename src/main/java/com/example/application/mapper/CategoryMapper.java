package com.example.application.mapper;

import com.example.application.config.MapperConfig;
import com.example.application.dto.category.CategoryDto;
import com.example.application.dto.category.CategoryRequestDto;
import com.example.application.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryRequestDto categoryRequestDto);
}
