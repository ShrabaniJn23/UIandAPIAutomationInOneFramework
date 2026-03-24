# https://automationexercise.com/
# https://automationexercise.com/api_list


Feature: User Management and Shopping Flow

  Background:
    Given the application is running

  Scenario: Create user via API, login via UI, add product to cart via UI, verify cart via API, and delete user via API
    When I create a new user via API with details from Excel "src/test/resources/data/users_new.xlsx" sheet "users"
    Then the user should be created successfully via API
    When I login to the application via UI with username and password from "src/test/resources/data/users_new.xlsx" sheet "users"
    Then I should be logged in successfully from UI and verify username from "src/test/resources/data/users_new.xlsx" sheet "users"

  #  When I add a product to the cart via UI:
  #    | productName | quantity |
   #   | Laptop      | 1        |
   # Then the product should be added to the cart

   # When I verify the cart contents via API for user "testuser"
  #  Then the cart should contain the product "Laptop" with quantity 1

   # When I delete the user via API with username "testuser"
  #  Then the user should be deleted successfully via API

 
    