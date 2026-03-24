package com.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    private WebDriver driver;

    @FindBy(id = "addToCartButton")
    private WebElement addToCartButton;

    @FindBy(id = "productName")
    private WebElement productNameField;

    @FindBy(id = "quantity")
    private WebElement quantityField;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(String productName, int quantity) {
        productNameField.clear();
        productNameField.sendKeys(productName);
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(quantity));
        addToCartButton.click();
    }

    public boolean isProductInCart(String productName) {
        // Check if product is in cart - simplified
        return driver.getPageSource().contains(productName);
    }
}