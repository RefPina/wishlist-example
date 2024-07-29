package com.example.wishlist.service;

import com.example.wishlist.model.Product;
import com.example.wishlist.model.Wishlist;

import java.util.Optional;

public interface WishlistService {
    Wishlist addProduct(String customerId, Product product);
    Wishlist removeProduct(String customerId, String productId);
    Optional<Wishlist> getWishlist(String customerId);
    Boolean isProductInWishlist(String customerId, String productId);
}
