package com.framework.utils;

import com.framework.config.ConfigReader;
import com.framework.constants.FrameworkConstants;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Explicit-wait helpers for PageFactory elements. The framework never relies on implicit waits.
 */
public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebDriverWait newWait(WebDriver driver) {
        long seconds = ConfigReader.getInt("explicit.wait.seconds",
                (int) FrameworkConstants.DEFAULT_EXPLICIT_WAIT.toSeconds());
        return new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    public static WebElement visible(WebDriver driver, WebElement element) {
        return newWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> allVisible(WebDriver driver, List<WebElement> elements) {
        return newWait(driver).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static WebElement clickable(WebDriver driver, WebElement element) {
        return newWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public static boolean invisible(WebDriver driver, WebElement element) {
        return newWait(driver).until(ExpectedConditions.invisibilityOf(element));
    }

    public static Alert alert(WebDriver driver) {
        return newWait(driver).until(ExpectedConditions.alertIsPresent());
    }

    public static WebDriver frame(WebDriver driver, String nameOrId) {
        return newWait(driver).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
    }
}
