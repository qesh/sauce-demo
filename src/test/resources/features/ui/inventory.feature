
@ui @product
Feature: Sauce Demo Product Page

  Background:
    Given the user is logged in

  Scenario: Adding a single item shows a badge of 1
    When I add one item to the cart
    Then the cart badge should show 1

  Scenario: Removing an item clears the badge
    Given I have added one item to the cart
    When I remove the item from the cart
    Then the cart badge should show 0

  Scenario: Adding several items shows the right count
    When I add 2 items to the cart
    Then the cart badge should show 2

  Scenario Outline: Sorting products by different options
    When I select "<SortOption>" from the sort dropdown
    Then the products should be sorted by "<SortOption>"

    Examples:
      | SortOption          |
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |