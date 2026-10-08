@ui @checkout
Feature: Sauce Demo checkout overview page
  Background:
    Given the user is logged in
    And I add 2 items to the cart
    And I open the cart
    And I proceed to checkout
    And I fill required fields
      | firstName | John     |
      | lastName  | Snow      |
      | zipCode   | 90210    |
    And I click continue
  @smoke @regression
  Scenario: User finalizing the order
    Then I verify page title as "Checkout: Overview"
    And shipping information has "Free Pony Express Delivery!"
    And total is $43.18

