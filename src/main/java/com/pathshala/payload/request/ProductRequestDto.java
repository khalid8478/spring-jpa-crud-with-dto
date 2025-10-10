package com.pathshala.payload.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {

    @NotBlank(message = "Product name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1")
    private Double price;

    @NotNull(message = "Unit is required")
    @Min(value = 1, message = "Unit must be greater than 0")
    private Long unit;

    @NotBlank(message = "Product category is required")
    private String productCategory;
}
