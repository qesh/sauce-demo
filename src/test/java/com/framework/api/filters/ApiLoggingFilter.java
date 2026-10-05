package com.framework.api.filters;

import com.framework.utils.JsonUtils;
import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import lombok.extern.slf4j.Slf4j;

/** Routes REST Assured request/response details to Log4j2 instead of stdout. */
@Slf4j
public class ApiLoggingFilter implements Filter {

    @Override
    public Response filter(FilterableRequestSpecification request,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {
        log.info("--> {} {}", request.getMethod(), request.getURI());
        if (request.getBody() != null) {
            log.debug("Request body:\n{}", JsonUtils.prettify(request.getBody().toString()));
        }
        Response response = ctx.next(request, responseSpec);
        log.info("<-- {} {} ({} ms)", response.getStatusCode(), request.getURI(), response.getTime());
        log.debug("Response body:\n{}", JsonUtils.prettify(response.asString()));
        return response;
    }
}
