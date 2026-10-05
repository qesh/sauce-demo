package com.framework.pages.saucedemo;

import com.framework.config.ConfigReader;
import com.framework.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[role='alert']")
    private WebElement alertMessage;



    public void login(String username, String password) {
        type(usernameInput, username);
        type(passwordInput, password);
        click(loginButton);
    }

    public void open() {
        navigateTo(ConfigReader.get("saucedemo.url"));
    }

    // Refactored approach in LoginPage.java
    public String getErrorMessage() {
        return textOf(alertMessage);
    }




}
