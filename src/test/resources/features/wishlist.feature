Feature: Wishlist management

  Scenario: Adding a product to the wishlist
    Given a customer with ID "123"
    When the customer adds a product with ID "001" and name "Sample Product" and price 29.99 and imageUrl "http://example.com/image.jpg" to the wishlist
    Then the wishlist should contain a product with ID "001" and name "Sample Product" and price 29.99 and imageUrl "http://example.com/image.jpg"

  Scenario: Removing a product from the wishlist
    Given a customer with ID "123"
    When the customer adds a product with ID "001" and name "Sample Product" and price 29.99 and imageUrl "http://example.com/image.jpg" to the wishlist
    And the customer removes the product with ID "001" from the wishlist
    Then the wishlist should not contain a product with ID "001"
