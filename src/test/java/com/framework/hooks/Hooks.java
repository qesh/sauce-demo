package com.framework.hooks;

import com.framework.context.ScenarioContext;
import com.framework.drivers.DriverFactory;
import com.framework.drivers.DriverManager;
import com.framework.utils.JsonUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.charset.StandardCharsets;

@Slf4j
public class Hooks {

    private final ScenarioContext context;

    public Hooks(ScenarioContext context) {
        this.context = context;
    }

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        context.setScenario(scenario);
        log.info("========== START: {} {} ==========", scenario.getName(), scenario.getSourceTagNames());
    }

    @Before(value = "@ui", order = 1)
    public void startBrowser() {
        DriverManager.setDriver(DriverFactory.createDriver());
    }

    @After(value = "@ui", order = 1)
    public void stopBrowser(Scenario scenario) {
        if (scenario.isFailed() && DriverManager.hasDriver()) {
            try {
                byte[] png = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(png, "image/png", "Failure screenshot");
            } catch (Exception e) {
                log.warn("Could not capture failure screenshot: {}", e.getMessage());
            }
        }
        DriverManager.quitDriver();
    }

    @After(value = "@api", order = 1)
    public void attachLastResponse(Scenario scenario) {
        Response response = context.getResponse();
        if (response != null) {
            String body = "HTTP " + response.getStatusCode() + System.lineSeparator()
                    + JsonUtils.prettify(response.asString());
            scenario.attach(body.getBytes(StandardCharsets.UTF_8), "text/plain", "Last API response");
        }
    }

    @After(order = 0)
    public void afterScenario(Scenario scenario) {
        log.info("========== END: {} -> {} ==========", scenario.getName(), scenario.getStatus());
    }
}
