
@tag
Feature: Purchase the order from Ecommerce website
  

  Background: 
  Given I landed on Ecommerce page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <name> and password <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name                      |  password       | productName |
      |jonathanbird@gmail.com     | Jonobird@1234   | ZARA COAT 3 | 
      