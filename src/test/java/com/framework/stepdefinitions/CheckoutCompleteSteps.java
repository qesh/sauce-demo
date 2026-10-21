package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.saucedemo.CheckOutCompletePage;
import io.cucumber.java.en.Then;
import org.testng.Assert;


public class CheckoutCompleteSteps {

    private CheckOutCompletePage checkOutCompletePage;

    private CheckOutCompletePage getCheckOutCompletePage(){
            if(checkOutCompletePage == null){
                checkOutCompletePage = new CheckOutCompletePage( DriverManager.getDriver());
            }
            return checkOutCompletePage;

    }


    @Then("I see the confirmation message {string}")
    public void i_see_the_confirmation_message(String message) {

        Assert.assertEquals(getCheckOutCompletePage().getCompleteHeaderText(), message);

    }



}
