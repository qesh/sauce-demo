package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.BasePage;
import com.framework.pages.saucedemo.CartPage;
import com.framework.pages.saucedemo.ProductPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CartSteps {

    private ProductPage productPage;

    private ProductPage getProductPage(){
        if (productPage == null){
            productPage = new ProductPage(DriverManager.getDriver());
        }
        return productPage;
    }



    private CartPage cartPage;

    private CartPage getCartPage(){
        if (cartPage == null){
            cartPage = new CartPage(DriverManager.getDriver());
        }
        return cartPage;
    }



    @When("I open the cart")
    public void i_open_the_cart() {
        getProductPage().openCart();
        Assert.assertTrue(getCartPage().isDisplayed());

    }
    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        getCartPage().checkOut();
    }

    @Then("cart quantity is {int}")
    public void cart_quantity_is(int quantity) {
        int actual = getProductPage().getCartCount();
        Assert.assertEquals(actual, quantity);

        System.out.println("Cart quantity is: ==========================>>  " + actual);

    }




}
