@ui
Feature: Sauce Demo login

  Background:
    Given the user is on the login page

  @smoke @regression
  Scenario Outline: Successful user authentication
    When the user logs in with username "<username>" and password "<password>"
    Then the product page should be displayed
    Examples:
      | username      | password     |
      | standard_user | secret_sauce |

  @negative @regression
  Scenario Outline: Unsuccessful user authentication
    When the user logs in with username "<username>" and password "<password>"
    Then the error message "<error_message>" should be displayed
    Examples:
      | username        | password     | error_message                                                             |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | standard_user   | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
      | ghost_user      | secret_sauce | Epic sadface: Username and password do not match any user in this service |
      |                 | secret_sauce | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |

