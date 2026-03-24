package com.example.steps;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class SearchSteps {
    private WebDriver driver;
    private HomePage homePage;

    @Given("I am on the home page")
    public void iAmOnTheHomePage() {
        // Setup driver if not already
        if (driver == null) {
            BaseTest.setupDriver();
            driver = BaseTest.getDriver();
        }
        // Navigate to home page
        driver.get("https://automationexercise.com/");
        homePage = new HomePage(driver);
    }

    @When("I search for {string}")
    public void iSearchFor(String query) {
        homePage = new HomePage(driver);
        homePage.searchFor(query);
    }

    @Then("I should see search results")
    public void iShouldSeeSearchResults() {
        // Assert search results are displayed
        Assert.assertTrue(homePage.getTitle().contains("automation"));
    }
}