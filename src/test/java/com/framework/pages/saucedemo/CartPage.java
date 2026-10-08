package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {


    public CartPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//span[@class='title']")
     private WebElement cartTitle;

    @FindBy(id = "checkout")
    private WebElement checkOutButton;

    public void  checkOut(){
        click(checkOutButton);
    }

    public boolean isDisplayed(){
        return isDisplayed(cartTitle);
    }


}
