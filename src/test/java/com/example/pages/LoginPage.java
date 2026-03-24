package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import dev.failsafe.internal.util.Assert;

public class LoginPage {
    private WebDriver driver;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = " //a[@href='/login']")
    private WebElement signupLoginButton;

    @FindBy(tagName = "h2")
    private WebElement loginpageText;

     @FindBy(xpath  = "//button[@data-qa = 'login-button']")
    private WebElement loginButton;

    @FindBy(tagName = "h2")
    private WebElement loggedInPageText;

    @FindBy(xpath  = "//i[@class='fa fa-user']/following-sibling::b")
    private WebElement LoggedinUserName;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        signupLoginButton.click();
       
        email.clear();
        email.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public String LoginStatus() {
        // Check for some element that indicates logged in state
       // String loggedInText = loggedInPageText.getText();
        String loggedInUser = LoggedinUserName.getText();

        return loggedInUser;
        // return driver.getCurrentUrl().contains("dashboard") || driver.getTitle().contains("Dashboard");
    }
}