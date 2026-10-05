package com.framework.drivers;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

/** Holds one WebDriver per thread so scenarios can run in parallel safely. */
@Slf4j
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException(
                    "No WebDriver for this thread. Is the scenario tagged @ui so the driver hook runs?");
        }
        return driver;
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                log.warn("Error while quitting WebDriver: {}", e.getMessage());
            } finally {
                DRIVER.remove();
            }
        }
    }
}
