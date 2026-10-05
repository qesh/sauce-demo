package com.framework.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private JsonUtils() {
    }

    public static String toPrettyJson(Object value) {
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Unable to serialise object to JSON", e);
        }
    }

    /** Pretty-prints a raw JSON string; returns the input unchanged if it is not valid JSON. */
    public static String prettify(String json) {
        if (json == null || json.isBlank()) {
            return "";
        }
        try {
            return MAPPER.writeValueAsString(MAPPER.readTree(json));
        } catch (JsonProcessingException e) {
            return json;
        }
    }
}
