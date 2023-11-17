package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private Long image;
    private int quanlity;
    private float price;
    private String texture;
    private float weight;
    private String size;
    private int trending;
}
