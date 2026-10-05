package com.framework.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;

/**
 * Runs each scenario as a TestNG data-provider row. Parallelism is controlled by
 * {@code data-provider-thread-count} in testng.xml (or {@code -Ddataproviderthreadcount=N}).
 */
public abstract class BaseRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
