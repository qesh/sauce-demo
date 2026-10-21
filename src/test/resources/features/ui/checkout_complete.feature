@ui @checkout
Feature: Sauce Demo checkout complete page
  Background:
    Given the user is logged in
    And I add 2 items to the cart
    And I open the cart
    And I proceed to checkout
    And I fill required fields
      | firstName | John  |
      | lastName  | Snow  |
      | zipCode   | 90210 |
    And I click continue
    And I click finish

  @smoke @regression
  Scenario: User completes the order
    Then I verify page title as "Checkout: Complete!"
    And I see the confirmation message "Thank you for your order!"


