package com.kaczmarm.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
public class Product {

    @Setter
    private Long id;

    private String name;
    @Setter
    private Integer price;
    @Setter
    private Integer quantity;
    @Setter
    private String description;
    private Category category;
    private LocalDateTime creationTime;
}
