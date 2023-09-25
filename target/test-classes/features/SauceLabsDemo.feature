Feature: Validate Sauce Labs E-Commerce Demo Scenario

  Scenario Outline: Validate Sauce Labs Shopping
  	Given I launch Sauce Labs Ecommerce website and verify "<title>"
    Given I login to Sauce Labs Ecommerce website "<username>" and "<password>"
    When I add multiple items to cart
    And I click Checkout
    And I enter user details
    And I click Finish button
    Then I verify the message "<message>"

    Examples: 
      | title |username|password| message |
      | Swag Labs |standard_user|secret_sauce|Thank you for your order!|

