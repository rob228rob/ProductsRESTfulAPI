package com.example.demo.models;

import lombok.*;

@Data
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private  String city;
    private String author;
    private double price;
}
