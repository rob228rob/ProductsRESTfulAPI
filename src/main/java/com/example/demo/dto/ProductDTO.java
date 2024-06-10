package com.example.demo.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String productName;

    private BigDecimal productPrice;
}
