package com.framework.constants;

import java.util.Arrays;

public enum BrowserType {
    CHROME, FIREFOX, EDGE;

    public static BrowserType from(String name) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(name.trim()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported browser '" + name + "'. Supported: " + Arrays.toString(values())));
    }
}
