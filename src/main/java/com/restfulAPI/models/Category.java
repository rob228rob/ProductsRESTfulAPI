package com.restfulAPI.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Category {
    Long id;
    String title;
    String description;

}
