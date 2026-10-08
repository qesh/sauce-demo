package com.framework.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/ui",
        glue = {"com.framework.stepdefinitions", "com.framework.hooks"},
        dryRun = false,
        tags = "@ui",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ui/cucumber.html",
                "json:target/cucumber-reports/ui/cucumber.json",
                "rerun:target/cucumber-reports/ui/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class UiTestRunner extends BaseRunner {
}
