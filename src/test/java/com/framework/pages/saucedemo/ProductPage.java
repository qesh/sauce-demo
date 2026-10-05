package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {


    public ProductPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(css = "[data-test='title']")
    private WebElement productTitle;

    public boolean isTitleDisplayed(){
        return isDisplayed(productTitle);
    }




}
