package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverview extends BasePage {
    public CheckoutOverview(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[data-test='shipping-info-value']")
    private WebElement shippingInformation;

    @FindBy(xpath = "//div[@class='summary_total_label']")
    private WebElement total;

    @FindBy(id = "finish")
    private WebElement finishButton;



    public String getTotalText(){
        return textOf(total);
    }

    public String getShippingInfo(){
        return textOf(shippingInformation);
    }

    public void clickFinish(){
        click(finishButton);
    }









}
