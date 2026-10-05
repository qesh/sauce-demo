package com.framework.pages;

import com.framework.utils.WaitUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Base for all Page Factory page objects. {@link PageFactory#initElements} wires every
 * {@code @FindBy} field of the subclass to a lazy proxy that locates the element on each use,
 * so stale-element issues after re-renders are avoided. Synchronisation is done with explicit waits.
 */
@Slf4j
public abstract class BasePage {

    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void navigateTo(String url) {
        log.info("Navigating to {}", url);
        driver.get(url);
    }

    protected void click(WebElement element) {
        WaitUtils.clickable(driver, element).click();
    }

    protected void type(WebElement element, String text) {
        WebElement visible = WaitUtils.visible(driver, element);
        visible.clear();
        visible.sendKeys(text);
    }

    protected String textOf(WebElement element) {
        return WaitUtils.visible(driver, element).getText().trim();
    }

    protected List<String> textsOf(List<WebElement> elements) {
        return WaitUtils.allVisible(driver, elements).stream()
                .map(e -> e.getText().trim())
                .toList();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return WaitUtils.visible(driver, element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
