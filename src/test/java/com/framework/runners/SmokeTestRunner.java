package com.framework.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {"com.framework.stepdefinitions", "com.framework.hooks"},
        dryRun = false,
        tags = "@ui and @smoke",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/smoke/cucumber.html",
                "json:target/cucumber-reports/smoke/cucumber.json",
                "rerun:target/cucumber-reports/smoke/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class SmokeTestRunner extends BaseRunner {
}
