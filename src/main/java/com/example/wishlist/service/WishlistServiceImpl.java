package com.example.wishlist.service;

import com.example.wishlist.model.Product;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final  WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public Wishlist addProduct(String customerId, Product product) {
        Wishlist wishlist = wishlistRepository.findById(customerId).orElse(new Wishlist());
        wishlist.setCustomerId(customerId);

        if (wishlist.getProducts().size() >= 20) {
            throw new RuntimeException("Wishlist cannot contain more than 20 products");
        }

        wishlist.getProducts().add(product);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist removeProduct(String customerId, String productId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findById(customerId);
        if (wishlistOptional.isPresent()) {
            Wishlist wishlist = wishlistOptional.get();
            wishlist.getProducts().removeIf(p -> p.getProductId().equals(productId));
            return wishlistRepository.save(wishlist);
        }
        return null;
    }

    @Override
    public Optional<Wishlist> getWishlist(String customerId) {
        return wishlistRepository.findById(customerId);
    }

    @Override
    public Boolean isProductInWishlist(String customerId, String productId) {
        Wishlist wishlist = wishlistRepository.findById(customerId).orElse(new Wishlist());
        return wishlist.getProducts().stream().anyMatch(product -> product.getProductId().equals(productId));
    }
}