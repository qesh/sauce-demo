package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.saucedemo.CheckoutCompletePage;
import io.cucumber.java.en.Then;
import org.testng.Assert;


public class CheckoutCompleteSteps {

    private CheckoutCompletePage checkoutCompletePage;

    private CheckoutCompletePage getCheckoutCompletePage(){
            if(checkoutCompletePage == null){
                checkoutCompletePage = new CheckoutCompletePage( DriverManager.getDriver());
            }
            return checkoutCompletePage;

    }


    @Then("I see the confirmation message {string}")
    public void i_see_the_confirmation_message(String message) {

        Assert.assertEquals(getCheckoutCompletePage().getCompleteHeaderText(), message);

    }



}
