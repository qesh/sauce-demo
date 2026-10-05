package com.framework.drivers;

import com.framework.config.ConfigReader;
import com.framework.constants.BrowserType;
import com.framework.constants.FrameworkConstants;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.Map;

/**
 * Creates configured WebDriver instances. Local drivers are resolved by Selenium Manager
 * (bundled with Selenium 4.6+), so no driver binaries or WebDriverManager setup is required.
 * Set {@code grid.url} to run against a Selenium Grid instead.
 */
@Slf4j
public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        BrowserType browser = BrowserType.from(ConfigReader.get("browser", "chrome"));
        boolean headless = ConfigReader.getBoolean("headless", false);
        String gridUrl = ConfigReader.get("grid.url", "");

        Capabilities options = switch (browser) {
            case CHROME -> chromeOptions(headless);
            case FIREFOX -> firefoxOptions(headless);
            case EDGE -> edgeOptions(headless);
        };

        WebDriver driver = gridUrl.isBlank() ? localDriver(browser, options) : remoteDriver(gridUrl, options);

        long pageLoad = ConfigReader.getInt("page.load.timeout.seconds",
                (int) FrameworkConstants.DEFAULT_PAGE_LOAD_TIMEOUT.toSeconds());
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        if (!headless) {
            driver.manage().window().maximize();
        }
        log.info("Started {} (headless={}, grid={})", browser, headless, gridUrl.isBlank() ? "none" : gridUrl);
        return driver;
    }

    private static WebDriver localDriver(BrowserType browser, Capabilities options) {
        return switch (browser) {
            case CHROME -> new ChromeDriver((ChromeOptions) options);
            case FIREFOX -> new FirefoxDriver((FirefoxOptions) options);
            case EDGE -> new EdgeDriver((EdgeOptions) options);
        };
    }

    private static WebDriver remoteDriver(String gridUrl, Capabilities options) {
        try {
            return new RemoteWebDriver(URI.create(gridUrl).toURL(), options);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid grid.url: " + gridUrl, e);
        }
    }

    /**
     * Defaults to EAGER: return once the DOM is ready instead of waiting for every third-party
     * resource (slow ones on herokuapp otherwise stall the renderer). Explicit waits cover the rest.
     */
    private static PageLoadStrategy pageLoadStrategy() {
        return PageLoadStrategy.valueOf(ConfigReader.get("page.load.strategy", "eager").toUpperCase());
    }

    private static ChromeOptions chromeOptions(boolean headless) {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(pageLoadStrategy());
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080", "--disable-notifications",
                "--no-sandbox", "--disable-dev-shm-usage", "--disable-search-engine-choice-screen");
        // Suppress Chrome's password manager / leaked-password dialogs that can block Sauce Demo logins.
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false));
        return options;
    }

    private static FirefoxOptions firefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(pageLoadStrategy());
        if (headless) {
            options.addArguments("-headless");
        }
        options.addArguments("--width=1920", "--height=1080");
        return options;
    }

    private static EdgeOptions edgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(pageLoadStrategy());
        if (headless) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--window-size=1920,1080", "--disable-notifications");
        return options;
    }
}
