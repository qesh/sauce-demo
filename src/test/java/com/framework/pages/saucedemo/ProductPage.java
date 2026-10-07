package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends BasePage {


    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private static final By CART_BADGE = By.cssSelector("[data-test='shopping-cart-badge']");

    @FindBy(css = "[data-test='title']")
    private WebElement productTitle;

    @FindBy(id = "shopping_cart_container")
    private WebElement cart;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement sortDropdown;

    @FindBy(css = "[data-test='inventory-item-name']")
    private List<WebElement> productNames;

    @FindBy(css = "[data-test='inventory-item-price']")
    private List<WebElement> productPrices;


    public boolean isTitleDisplayed() {
        return isDisplayed(productTitle);
    }

    // Button ids follow the pattern add-to-cart-<slug> / remove-<slug>,
    // e.g. "Sauce Labs Backpack" -> add-to-cart-sauce-labs-backpack
    public void addToCart(String productName) {
        click(driver.findElement(By.id("add-to-cart-" + toSlug(productName))));
    }

    public void removeFromCart(String productName) {
        click(driver.findElement(By.id("remove-" + toSlug(productName))));
    }

    public boolean isInCart(String productName) {
        return !driver.findElements(By.id("remove-" + toSlug(productName))).isEmpty();
    }

    // The badge is removed from the page when the cart is empty
    public int getCartCount() {
        List<WebElement> badge = driver.findElements(CART_BADGE);
        return badge.isEmpty() ? 0 : Integer.parseInt(badge.get(0).getText().trim());
    }

    public void openCart() {
        click(cart);
    }

    // Takes the text shown in the dropdown, e.g. "Price (low to high)"
    public void sortBy(String optionText) {
        new Select(sortDropdown).selectByVisibleText(optionText);
    }

    public String getSelectedSortOption() {
        return new Select(sortDropdown).getFirstSelectedOption().getText().trim();
    }

    public List<String> getProductNames() {
        return textsOf(productNames);
    }

    public List<Double> getProductPrices() {
        return textsOf(productPrices).stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();
    }

    private String toSlug(String productName) {
        return productName.toLowerCase().replace(" ", "-");
    }
}
