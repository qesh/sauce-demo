package com.framework.api.endpoints;

import com.framework.api.filters.ApiLoggingFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public abstract class BaseEndpoint {

    private final RequestSpecification baseSpec;

    protected BaseEndpoint(String baseUri) {
        this.baseSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                // A single media type: ContentType.JSON expands to a list that Restful Booker answers with 418
                .setAccept("application/json")
                .addFilter(new ApiLoggingFilter())
                .build();
    }

    /** Fresh request built on the shared base specification. */
    protected RequestSpecification request() {
        return given().spec(baseSpec);
    }
}
