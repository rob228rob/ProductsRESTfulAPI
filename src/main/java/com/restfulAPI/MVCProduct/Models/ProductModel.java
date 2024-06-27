package com.restfulAPI.MVCProduct.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ProductModel {
    private Long id;
    private String name;
    private Double price;

}
