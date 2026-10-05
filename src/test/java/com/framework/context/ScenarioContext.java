package com.framework.context;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Per-scenario shared state. PicoContainer creates a fresh instance for every scenario and
 * injects it into any hook or step definition class that declares it as a constructor argument.
 */
@Getter
@Setter
public class ScenarioContext {

    private Scenario scenario;
    /** Last API response; the {@code @api} hook attaches it to the report. */
    private Response response;
    private final Map<String, Object> data = new HashMap<>();

    public void put(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key, Class<T> type) {
        Object value = data.get(key);
        if (value == null) {
            throw new IllegalStateException("No value stored in scenario context for " + key);
        }
        return type.cast(value);
    }
}
