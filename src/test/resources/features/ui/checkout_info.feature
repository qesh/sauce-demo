@ui @checkout
Feature: Sauce Demo checkout info
  Background:
    Given the user is logged in
    And I add 2 items to the cart
    And I open the cart
    And I proceed to checkout
  @smoke @regression
  Scenario: User filling checking info
    Then I verify page title as "Checkout: Your Information"
    When I fill required fields
      | firstName | John     |
      | lastName  | Snow      |
      | zipCode   | 90210    |
    And I click continue
    Then I verify page title as "Checkout: Overview"