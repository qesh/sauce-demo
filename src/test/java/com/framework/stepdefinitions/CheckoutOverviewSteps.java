package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.saucedemo.CheckoutOverview;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class CheckoutOverviewSteps {

    private CheckoutOverview checkoutOverview;

    private CheckoutOverview getCheckoutOverview(){
        if(checkoutOverview==null){
            checkoutOverview = new CheckoutOverview(DriverManager.getDriver());
        }
        return checkoutOverview;
    }


    @Then("shipping information has {string}")
    public void shipping_information_has(String shippingInfo) {
        Assert.assertEquals(getCheckoutOverview().getShippingInfo(), shippingInfo);

    }
    @Then("total is ${double}")
    public void total_is(double expectedTotal) {

        String rawText = getCheckoutOverview().getTotalText();
        String cleanAmount = rawText.replaceAll("[^0-9.]", "");

        double actualTotal = Double.parseDouble(cleanAmount);

        Assert.assertEquals(actualTotal, expectedTotal,  0.001);

    }




}
