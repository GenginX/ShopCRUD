package com.kaczmarm.dto;

import com.kaczmarm.domain.Category;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CreateProductDto {

    @NotNull
    private String name;

    @NotNull
    @Min(1)

    private Integer price;
    @NotNull
    @Min(1)

    private Integer quantity;

    @NotNull
    private String description;

    @NotNull
    private Category category;

}
