@ui
Feature: Sauce Demo checkout

  Background:
    Given the user is logged in
  @smoke @regression
  Scenario: Cart shows correct quantity
    When I add 2 items to the cart
    And I open the cart
    Then cart quantity is 2


