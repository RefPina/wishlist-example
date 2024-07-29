package com.example.wishlist.controller;

import com.example.wishlist.model.Product;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @PostMapping("/{customerId}/products")
    public ResponseEntity<Wishlist> addProduct(@PathVariable String customerId, @RequestBody Product product) {
        Wishlist wishlist = wishlistService.addProduct(customerId, product);
        return ResponseEntity.ok(wishlist);
    }

    @DeleteMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Wishlist> removeProduct(@PathVariable String customerId, @PathVariable String productId) {
        Wishlist wishlist = wishlistService.removeProduct(customerId, productId);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Wishlist> getWishlist(@PathVariable String customerId) {
        Optional<Wishlist> wishlist = wishlistService.getWishlist(customerId);
        return wishlist.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{customerId}/products/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable String customerId, @PathVariable String productId) {
        boolean isInWishlist = wishlistService.isProductInWishlist(customerId, productId);
        return ResponseEntity.ok(isInWishlist);
    }
}