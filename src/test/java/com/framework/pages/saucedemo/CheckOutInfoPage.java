package com.framework.pages.saucedemo;

import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutInfoPage extends BasePage {


    public CheckOutInfoPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[@class='title']")
    private WebElement title;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement zipCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    public String getTitleText() {
        return textOf(title);
    }

    public void fillFirstName(String name) {
        type(firstName, name);
    }
    public void fillLastName(String surname) {
        type(lastName, surname);
    }

    public void fillPostalCode(String postalCode) {
        type(zipCode, postalCode);
    }

    public void continueButton() {
        click(continueButton);
    }








}
