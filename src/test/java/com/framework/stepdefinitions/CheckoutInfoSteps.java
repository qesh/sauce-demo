package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.saucedemo.CheckOutInfoPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import java.util.Map;

public class CheckoutInfoSteps {

    private CheckOutInfoPage checkOutInfoPage;

    private CheckOutInfoPage getCheckOutInfoPage() {
        if(checkOutInfoPage==null) {
            checkOutInfoPage = new CheckOutInfoPage(DriverManager.getDriver());
        }
        return checkOutInfoPage;
    }

    @Then("I verify page title as {string}")
    public void i_verify_page_title_as(String title) {

        Assert.assertEquals(getCheckOutInfoPage().getTitleText(), title);

    }

    @When("I fill required fields")
    public void i_fill_required_fields(Map<String, String> data) {

        String name = data.get("firstName");
        String lastname = data.get("lastName");
        String zipcode = data.get("zipCode");

        getCheckOutInfoPage().fillFirstName(name);
        getCheckOutInfoPage().fillLastName(lastname);
        getCheckOutInfoPage().fillPostalCode(zipcode);


    }

    @When("I click continue")
    public void i_click_continue() {

        getCheckOutInfoPage().continueButton();

        System.out.println("Checkout Info page is completed and clicked Continue Button =============================>> CONTINUE");

    }



}
