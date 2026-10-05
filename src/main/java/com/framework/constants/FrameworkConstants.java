package com.framework.constants;

import java.time.Duration;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    public static final String CONFIG_FILE = "config.properties";
    public static final String SCHEMA_DIR = "schemas/";

    public static final Duration DEFAULT_EXPLICIT_WAIT = Duration.ofSeconds(10);
    public static final Duration DEFAULT_PAGE_LOAD_TIMEOUT = Duration.ofSeconds(30);

    public static final String DATE_FORMAT = "yyyy-MM-dd";
}