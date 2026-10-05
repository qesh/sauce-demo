package com.framework.config;

import com.framework.constants.FrameworkConstants;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Resolves configuration with the following precedence (highest first):
 * <ol>
 *     <li>JVM system property, e.g. {@code -Dbrowser=firefox}</li>
 *     <li>Environment variable, upper-cased with dots replaced by underscores, e.g. {@code BROWSER}</li>
 *     <li>{@code config.properties} on the classpath</li>
 * </ol>
 */
@Slf4j
public final class ConfigReader {

    private static final Properties PROPERTIES = load();

    private ConfigReader() {
    }

    private static Properties load() {
        Properties properties = new Properties();
        try (InputStream in = ConfigReader.class.getClassLoader()
                .getResourceAsStream(FrameworkConstants.CONFIG_FILE)) {
            if (in == null) {
                throw new IllegalStateException(FrameworkConstants.CONFIG_FILE + " not found on the classpath");
            }
            properties.load(in);
            log.info("Loaded {} configuration entries from {}", properties.size(), FrameworkConstants.CONFIG_FILE);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read " + FrameworkConstants.CONFIG_FILE, e);
        }
        return properties;
    }

    public static String get(String key) {
        String value = resolve(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required configuration key: " + key);
        }
        return value.trim();
    }

    public static String get(String key, String defaultValue) {
        String value = resolve(key);
        return value == null || value.isBlank() ? defaultValue : value.trim();
    }

    public static int getInt(String key, int defaultValue) {
        return Integer.parseInt(get(key, String.valueOf(defaultValue)));
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultValue)));
    }

    private static String resolve(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key.toUpperCase().replace('.', '_'));
        }
        if (value == null) {
            value = PROPERTIES.getProperty(key);
        }
        return value;
    }
}