package com.framework.stepdefinitions;

import com.framework.drivers.DriverManager;
import com.framework.pages.saucedemo.CheckoutOverviewPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class CheckoutOverviewSteps {

    private CheckoutOverviewPage checkoutOverview;

    private CheckoutOverviewPage getCheckoutOverview(){
        if(checkoutOverview==null){
            checkoutOverview = new CheckoutOverviewPage(DriverManager.getDriver());
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

    @When("I click finish")
    public void i_click_finish() {
        getCheckoutOverview().clickFinish();
    }




}
