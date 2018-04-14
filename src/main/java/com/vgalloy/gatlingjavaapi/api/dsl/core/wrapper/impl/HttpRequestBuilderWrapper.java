package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import io.gatling.core.body.Body;
import io.gatling.core.body.StringBody;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.request.builder.HttpRequestBuilder;

import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expressions;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpRequestBuilderWrapper implements Supplier<HttpRequestBuilder> {

    private final HttpRequestBuilder httpRequestBuilder;

    public HttpRequestBuilderWrapper(HttpRequestBuilder httpRequestBuilder) {
        this.httpRequestBuilder = Objects.requireNonNull(httpRequestBuilder);
    }

    @Override
    public HttpRequestBuilder get() {
        return httpRequestBuilder;
    }

    public HttpRequestBuilderWrapper headers(Map<String, String> headers) {
        Objects.requireNonNull(headers);

        scala.collection.immutable.Map<String, String> map = ScalaHelper.map(headers);
        return new HttpRequestBuilderWrapper(httpRequestBuilder.headers(map));
    }

    public HttpRequestBuilderWrapper header(String name, String value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);

        return new HttpRequestBuilderWrapper(httpRequestBuilder.header(name, Expressions.of(value)));
    }

    public HttpRequestBuilderWrapper formParam(String name, Object value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);

        return new HttpRequestBuilderWrapper(httpRequestBuilder.formParam(Expressions.of(name), Expressions.of(value)));
    }

    public HttpRequestBuilderWrapper body(String body) {
        Objects.requireNonNull(body);

        Body stringBody = new StringBody(Expressions.of(body), GatlingConfigurationSupplier.GATLING_CONFIGURATION);
        return new HttpRequestBuilderWrapper(httpRequestBuilder.body(stringBody));
    }

    public HttpRequestBuilderWrapper check(HttpCheck httpCheck) {
        Objects.requireNonNull(httpCheck);

        return new HttpRequestBuilderWrapper(httpRequestBuilder.check(ScalaHelper.map(httpCheck)));
    }
}
