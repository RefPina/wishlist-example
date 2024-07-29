package com.example.wishlist.stepdefinitions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.java.en.*;
import org.junit.Assert;
import com.example.wishlist.model.Product;
import com.example.wishlist.model.Wishlist;
import com.example.wishlist.service.WishlistService;

import java.util.Optional;

@SpringBootTest
public class WishlistStepDefinitions {

    private String customerId;
    private Product product;
    private Wishlist wishlist;

    private final WishlistService wishlistService;

    public WishlistStepDefinitions(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @Given("a customer with ID {string}")
    public void aCustomerWithID(String customerId) {
        this.customerId = customerId;
    }

    @When("the customer adds a product with ID {string} and name {string} and price {double} and imageUrl {string} to the wishlist")
    public void theCustomerAddsProductToWishlist(String productId, String name, double price, String imageUrl) {
        product = new Product(productId, name, price, imageUrl);
        wishlist = wishlistService.addProduct(customerId, product);
    }

    @When("the customer removes the product with ID {string} from the wishlist")
    public void theCustomerRemovesProductFromWishlist(String productId) {
        wishlist = wishlistService.removeProduct(customerId, productId);
    }

    @When("the customer retrieves the wishlist")
    public void theCustomerRetrievesWishlist() {
        Optional<Wishlist> optionalWishlist = wishlistService.getWishlist(customerId);
        optionalWishlist.ifPresent(value -> wishlist = value);
    }

    @When("the customer checks if the product with ID {string} is in the wishlist")
    public void theCustomerChecksIfProductInWishlist(String productId) {
        boolean isInWishlist = wishlistService.isProductInWishlist(customerId, productId);
        Assert.assertTrue(isInWishlist);
    }

    @Then("the wishlist should contain a product with ID {string} and name {string} and price {double} and imageUrl {string}")
    public void theWishlistShouldContainProduct(String productId, String name, double price, String imageUrl) {
        boolean productExists = wishlist.getProducts().stream()
                .anyMatch(p -> p.getProductId().equals(productId) && p.getName().equals(name) && p.getPrice() == price && p.getImageUrl().equals(imageUrl));
        Assert.assertTrue(productExists);
    }

    @Then("the wishlist should not contain a product with ID {string}")
    public void theWishlistShouldNotContainProduct(String productId) {
        boolean productExists = wishlist.getProducts().stream()
                .anyMatch(p -> p.getProductId().equals(productId));
        Assert.assertFalse(productExists);
    }

    @Then("the result should be {string}")
    public void theResultShouldBe(String expectedResult) {
        boolean result = Boolean.parseBoolean(expectedResult);
        Assert.assertEquals(result, wishlistService.isProductInWishlist(customerId, product.getProductId()));
    }
}
