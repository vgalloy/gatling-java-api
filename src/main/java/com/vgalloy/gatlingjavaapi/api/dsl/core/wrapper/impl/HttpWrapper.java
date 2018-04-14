package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl;

import java.util.Objects;

import io.gatling.http.request.builder.Http;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpWrapper {

    private final Http http;

    public HttpWrapper(Http http) {
        this.http = Objects.requireNonNull(http);
    }

    public HttpRequestBuilderWrapper get(String requestUrl) {
        Objects.requireNonNull(requestUrl);

        Expression<String> requestExpression = Expression.of(requestUrl);
        return new HttpRequestBuilderWrapper(http.get(requestExpression));
    }

    public HttpRequestBuilderWrapper put(String requestUrl) {
        Objects.requireNonNull(requestUrl);

        Expression<String> requestExpression = Expression.of(requestUrl);
        return new HttpRequestBuilderWrapper(http.put(requestExpression));
    }

    public HttpRequestBuilderWrapper post(String requestUrl) {
        Objects.requireNonNull(requestUrl);

        Expression<String> requestExpression = Expression.of(requestUrl);
        return new HttpRequestBuilderWrapper(http.post(requestExpression));
    }

    public HttpRequestBuilderWrapper head(String requestUrl) {
        Objects.requireNonNull(requestUrl);

        Expression<String> requestExpression = Expression.of(requestUrl);
        return new HttpRequestBuilderWrapper(http.head(requestExpression));
    }

    public HttpRequestBuilderWrapper patch(String requestUrl) {
        Objects.requireNonNull(requestUrl);

        Expression<String> requestExpression = Expression.of(requestUrl);
        return new HttpRequestBuilderWrapper(http.patch(requestExpression));
    }


    public HttpRequestBuilderWrapper delete(String requestUrl) {
        Objects.requireNonNull(requestUrl);

        Expression<String> requestExpression = Expression.of(requestUrl);
        return new HttpRequestBuilderWrapper(http.delete(requestExpression));
    }
}
