package com.example.application.dto.book;

import java.math.BigDecimal;

public record BookSearchParametersDto(
        String[] authors, String[] titles,
        BigDecimal priceFrom, BigDecimal priceTo) {
}
