package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.saucedemo.CheckoutInfoPage;
import io.cucumber.java.en.*;
import org.testng.Assert;
import java.util.Map;

public class CheckoutInfoSteps {

    private CheckoutInfoPage checkoutInfoPage;

    private CheckoutInfoPage getCheckoutInfoPage() {
        if(checkoutInfoPage ==null) {
            checkoutInfoPage = new CheckoutInfoPage(DriverManager.getDriver());
        }
        return checkoutInfoPage;
    }

    @Then("I verify page title as {string}")
    public void i_verify_page_title_as(String title) {

        Assert.assertEquals(getCheckoutInfoPage().getTitleText(), title);

    }

    @When("I fill required fields")
    public void i_fill_required_fields(Map<String, String> data) {

        String name = data.get("firstName");
        String lastname = data.get("lastName");
        String zipcode = data.get("zipCode");

        getCheckoutInfoPage().fillFirstName(name);
        getCheckoutInfoPage().fillLastName(lastname);
        getCheckoutInfoPage().fillPostalCode(zipcode);


    }

    @When("I click continue")
    public void i_click_continue() {

        getCheckoutInfoPage().continueButton();

        System.out.println("Checkout Info page is completed and clicked Continue Button =============================>> CONTINUE");

    }

    @When("I fill the checkout form with {string}, {string} and {string}")
    public void i_fill_checkout_form_with(String name, String lastname, String zipcode) {
        getCheckoutInfoPage().fillFirstName(name);
        getCheckoutInfoPage().fillLastName(lastname);
        getCheckoutInfoPage().fillPostalCode(zipcode);
    }

    @Then("I see the checkout error {string}")
    public void i_see_the_checkout_error(String errorMessage) {
        Assert.assertEquals(getCheckoutInfoPage().getErrorText(), errorMessage);
    }



}
