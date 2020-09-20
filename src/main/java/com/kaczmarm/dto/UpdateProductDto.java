package com.kaczmarm.dto;

import lombok.Data;

@Data
public class UpdateProductDto {

    private Integer price;

    private Integer quantity;

    private String description;

}
