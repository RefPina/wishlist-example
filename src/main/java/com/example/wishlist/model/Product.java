package com.example.wishlist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private String productId;
    private String name;
    private Double price;
    private String imageUrl;
}

