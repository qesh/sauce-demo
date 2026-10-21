package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutCompletePage extends BasePage {
    public CheckOutCompletePage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "back-to-products")
    private WebElement backHomeBtn;

    @FindBy(xpath = "//h2[normalize-space()='Thank you for your order!']")
    private WebElement completeheader;


    public String getCompleteHeaderText(){
        return textOf(completeheader);
    }

    public void clickOnBackButton(){
        click(backHomeBtn);
    }

    @FindBy(css = ".title")
    private WebElement title;

    public void clickFinish(){

    }




}
