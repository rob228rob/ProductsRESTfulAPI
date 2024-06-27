package com.restfulAPI.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private String productName;

    private BigDecimal productPrice;
}
