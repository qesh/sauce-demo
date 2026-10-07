package com.framework.stepdefinitions;

import com.framework.config.ConfigReader;
import com.framework.pages.saucedemo.LoginPage;
import com.framework.pages.saucedemo.ProductPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.framework.drivers.DriverManager;
import org.testng.Assert;


public class LoginSteps {
    private LoginPage loginPage;
    private ProductPage productPage;

    private LoginPage getLoginPage(){
        if (loginPage == null){
            loginPage = new LoginPage(DriverManager.getDriver());
        }
        return loginPage;
    }
    private ProductPage getProductPage(){
        if (productPage == null){
            productPage = new ProductPage(DriverManager.getDriver());
        }
        return productPage;
    }

    @Given("the user is on the login page")
    public void the_user_is_on_the_login_page() {

        getLoginPage().open();
    }
    @When("the user logs in with username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String username, String password) {
        getLoginPage().login(username, password);

    }
    @Then("the product page should be displayed")
    public void the_product_page_should_be_displayed() {
      Assert.assertTrue(getProductPage().isTitleDisplayed(), "Products page was not displayed after login");

    }

    @Then("the error message {string} should be displayed")
    public void the_error_message_should_be_displayed(String expectedErrorMessage) {
        String actualErrorMessage = getLoginPage().getErrorMessage();
        Assert.assertTrue(actualErrorMessage.equals(expectedErrorMessage),"Wrong error message shown on the login page");

    }



    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        getLoginPage().open();
        getLoginPage().login(
                ConfigReader.get("saucedemo.username"),
                ConfigReader.get("saucedemo.password"));
        Assert.assertTrue(getProductPage().isTitleDisplayed(), "Login failed, products page not shown");
    }




}
