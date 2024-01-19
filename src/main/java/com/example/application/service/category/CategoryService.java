package com.example.application.service.category;

import com.example.application.dto.category.CategoryDto;
import com.example.application.dto.category.CategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getCategoryById(Long id);

    CategoryDto save(CategoryRequestDto categoryRequestDto);

    CategoryDto update(Long id, CategoryRequestDto categoryRequestDto);

    void deleteById(Long id);
}
