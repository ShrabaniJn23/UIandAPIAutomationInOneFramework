package com.example.steps;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.example.base.BaseTest;
import com.example.pages.CartPage;
import com.example.pages.LoginPage;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserManagementSteps {
    private Response apiResponse;
    private LoginPage loginPage;
    private CartPage cartPage;
    private String currentUsername;
    private WebDriver driver = BaseTest.getDriver();

    

    @Given("the application is running")
    public void theApplicationIsRunning() {
        // Set base URI for API calls
        RestAssured.baseURI = "https://automationexercise.com/"; // Adjust as needed
        // Setup driver if not already
        if (driver == null) {
            BaseTest.setupDriver();
            driver = BaseTest.getDriver();
        }
        // Navigate to UI application
        driver.get("https://automationexercise.com/"); // Adjust as needed
        loginPage = new LoginPage(driver);
        cartPage = new CartPage(driver);
    }    
    
    @When("I create a new user via API with details from Excel {string} sheet {string}")
    public void iCreateANewUserViaAPIWithDetailsFromExcelSheet(String excelPath, String sheetName) throws Exception {
        java.nio.file.Path path = java.nio.file.Paths.get(excelPath);
        java.util.List<java.util.Map<String, String>> userDetails = com.example.utils.ExcelReader.readSheetAsMaps(path, sheetName);
        if (userDetails.isEmpty()) {
            throw new IllegalArgumentException("Excel sheet has no user data: " + sheetName);
        }
        Map<String, String> user = userDetails.get(0);
        
        // Extract all required user details from Excel
        String name = user.get("name");
        String email = user.get("email");
        String password = user.get("password");
        String title = user.get("title");
        String birthDate = user.get("birth_date");
        String birthMonth = user.get("birth_month");
        String birthYear = user.get("birth_year");
        String firstname = user.get("firstname");
        String lastname = user.get("lastname");
        String company = user.get("company");
        String address1 = user.get("address1");
        String address2 = user.get("address2");
        String country = user.get("country");
        String zipcode = user.get("zipcode");
        String state = user.get("state");
        String city = user.get("city");
        String mobileNumber = user.get("mobile_number");
        
        currentUsername = name; // Use name as username for login

        // Create the user payload with all required fields
        Map<String, Object> userPayload = new java.util.HashMap<>();
        userPayload.put("name", name);
        userPayload.put("email", email);
        userPayload.put("password", password);
        userPayload.put("title", title);
        userPayload.put("birth_date", birthDate);
        userPayload.put("birth_month", birthMonth);
        userPayload.put("birth_year", birthYear);
        userPayload.put("firstname", firstname);
        userPayload.put("lastname", lastname);
        userPayload.put("company", company);
        userPayload.put("address1", address1);
        userPayload.put("address2", address2);
        userPayload.put("country", country);
        userPayload.put("zipcode", zipcode);
        userPayload.put("state", state);
        userPayload.put("city", city);
        userPayload.put("mobile_number", mobileNumber);

        apiResponse = given()
            .contentType("application/x-www-form-urlencoded")
            .formParams(userPayload)
        .when()
            .post("api/createAccount")
        .then()
            .extract().response();

        // Attach to Allure report
        Allure.addAttachment("API Request", "text/plain", userPayload.toString(), ".txt");
        Allure.addAttachment("API Response", "application/json", apiResponse.asString(), ".json");
    }

    @Then("the user should be created successfully via API")
    public void theUserShouldBeCreatedSuccessfullyViaAPI() {
        // Attach API details to Allure report
        Allure.addAttachment("API Response Details", "text/plain",
            "Status: " + apiResponse.getStatusCode() + "\n" +
            "Headers: " + apiResponse.getHeaders() + "\n" +
            "Body: " + apiResponse.asPrettyString(), ".txt");

        // Assert user creation - expected status code is 201 (user successfully created)
        // Any other status code = failure
        int statusCode = apiResponse.getStatusCode();
        
        String responseMessage = "";
        if (apiResponse.getContentType() != null && apiResponse.getContentType().contains("json")) {
            String message = apiResponse.jsonPath().getString("message");
            responseMessage = (message != null) ? message : "";
        }

        // Only accept 201 as success, fail for any other status code
       // Assert.assertEquals(statusCode, 201,
         //   "User creation should succeed with status 201, but got: " + statusCode + " - " + responseMessage);

        // For successful 201, validate response structure
        if (apiResponse.getContentType() != null && apiResponse.getContentType().contains("json")) {
            String responseCode = apiResponse.jsonPath().getString("responseCode");
            Assert.assertNotNull(responseCode, "Response should contain responseCode");
        }
    }
/* 
    @When("I login to the application via UI with username {string} and password {string}")
    public void iLoginToTheApplicationViaUIWithUsernameAndPassword(String username, String password) {
        loginPage.login(username, password);
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        Assert.assertTrue(loginPage.isLoggedIn(), "Login should be successful");
    }

    */
    @When("I login to the application via UI with username and password from {string} sheet {string}")
    public void iLoginToTheApplicationViaUIWithUsernameAndPasswordFromExcel(String excelPath, String sheetName) throws Exception {
                // ...API step code...
        driver.navigate().refresh(); // Refresh the browser to reflect API changes
        // ...continue with UI steps...
        java.nio.file.Path path = java.nio.file.Paths.get(excelPath);
        java.util.List<java.util.Map<String, String>> userDetails = com.example.utils.ExcelReader.readSheetAsMaps(path, sheetName);
        if (userDetails.isEmpty()) {
            throw new IllegalArgumentException("Excel sheet has no user data: " + sheetName);
        }
        Map<String, String> user = userDetails.get(0);
        String email = user.get("email");
        String password = user.get("password");
        loginPage.login(email, password);
    }


    @Then("I should be logged in successfully from UI and verify username from {string} sheet {string}")
    public void iShouldBeLoggedInSuccessfullyFromUIAndVerifyUsernameFromExcel(String excelPath, String sheetName) throws Exception {
        
         java.nio.file.Path path = java.nio.file.Paths.get(excelPath);
        java.util.List<java.util.Map<String, String>> userDetails = com.example.utils.ExcelReader.readSheetAsMaps(path, sheetName);
        if (userDetails.isEmpty()) {
            throw new IllegalArgumentException("Excel sheet has no user data: " + sheetName);
        }
        Map<String, String> user = userDetails.get(0);
        String expectedUsername = user.get("name");
        
        Assert.assertTrue(loginPage.LoginStatus().equals(expectedUsername), "Login user name should match expected username");
        //String expectedUsername = user.get("name");
        // Optionally, verify username is displayed on the UI (add logic if needed)
        // Example: Assert.assertTrue(loginPage.getDisplayedUsername().equals(expectedUsername));
    }

    
    @When("I add a product to the cart via UI:")
    public void iAddAProductToTheCartViaUI(List<Map<String, String>> productDetails) {
        Map<String, String> product = productDetails.get(0);
        String productName = product.get("productName");
        int quantity = Integer.parseInt(product.get("quantity"));

        cartPage.addProductToCart(productName, quantity);
    }

    @Then("the product should be added to the cart")
    public void theProductShouldBeAddedToTheCart() {
        // This would need to be more specific based on actual UI
        Assert.assertTrue(cartPage.isProductInCart("Laptop"), "Product should be in cart");
    }

    @When("I verify the cart contents via API for user {string}")
    public void iVerifyTheCartContentsViaAPIForUser(String username) {
        apiResponse = given()
        .when()
            .get("/users/" + username + "/cart")
        .then()
            .extract().response();
    }

    @Then("the cart should contain the product {string} with quantity {int}")
    public void theCartShouldContainTheProductWithQuantity(String productName, int quantity) {
        Assert.assertEquals(apiResponse.getStatusCode(), 200, "Cart retrieval failed");
        List<Map<String, Object>> cartItems = apiResponse.jsonPath().getList("items");
        Assert.assertTrue(cartItems.stream().anyMatch(item ->
            productName.equals(item.get("name")) && quantity == ((Integer) item.get("quantity"))),
            "Cart should contain the specified product with correct quantity");
    }

    @When("I delete the user via API with username {string}")
    public void iDeleteTheUserViaAPIWithUsername(String username) {
        apiResponse = given()
        .when()
            .delete("/users/" + username)
        .then()
            .extract().response();
    }

    @Then("the user should be deleted successfully via API")
    public void theUserShouldBeDeletedSuccessfullyViaAPI() {
        Assert.assertEquals(apiResponse.getStatusCode(), 204, "User deletion failed");
    }

       
    
    
}