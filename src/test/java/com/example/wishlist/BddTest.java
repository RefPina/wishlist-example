package com.example.wishlist;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.example.wishlist.stepdefinitions", "com.example.wishlist.config"},
        plugin = {"pretty"}
)
public class BddTest {
}
