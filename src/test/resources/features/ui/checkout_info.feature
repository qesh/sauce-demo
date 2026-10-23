@ui @checkout
Feature: Sauce Demo checkout info
  Background:
    Given the user is logged in
    And I add 2 items to the cart
    And I open the cart
    And I proceed to checkout
  @smoke @regression
  Scenario: User filling checkout info
    Then I verify page title as "Checkout: Your Information"
    When I fill required fields
      | firstName | John     |
      | lastName  | Snow      |
      | zipCode   | 90210    |
    And I click continue
    Then I verify page title as "Checkout: Overview"


  @regression
  Scenario Outline: Checkout info validation
    When I fill the checkout form with "<firstName>", "<lastName>" and "<zipCode>"
    And I click continue
    Then I see the checkout error "<error>"
    Examples:
      | firstName | lastName | zipCode | error                          |
      |           | Snow     | 90210   | Error: First Name is required  |
      | John      |          | 90210   | Error: Last Name is required   |
      | John      | Snow     |         | Error: Postal Code is required |