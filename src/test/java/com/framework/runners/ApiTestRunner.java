package com.framework.runners;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/api",
        glue = {"com.framework.stepdefinitions", "com.framework.hooks"},
        tags = "@api and not @wip",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/api/cucumber.html",
                "json:target/cucumber-reports/api/cucumber.json",
                "rerun:target/cucumber-reports/api/rerun.txt",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true
)
public class ApiTestRunner extends BaseRunner {
}
