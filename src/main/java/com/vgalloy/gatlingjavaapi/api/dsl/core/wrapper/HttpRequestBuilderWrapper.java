package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.http.request.builder.HttpRequestBuilder;

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

        return new HttpRequestBuilderWrapper(httpRequestBuilder.header(name, ScalaHelper.toExpression(value)));
    }

    public HttpRequestBuilderWrapper formParam(String name, Object value) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(value);

        return new HttpRequestBuilderWrapper(httpRequestBuilder.formParam(ScalaHelper.toExpression(name), ScalaHelper.toExpression(value)));
    }
}
