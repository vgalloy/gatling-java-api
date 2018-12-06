package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import java.util.Objects;
import java.util.function.Supplier;

import io.gatling.http.protocol.HttpProtocolBuilder;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpProtocolBuilderWrapper implements Supplier<HttpProtocolBuilder> {

    private final HttpProtocolBuilder httpProtocolBuilder;

    public HttpProtocolBuilderWrapper(HttpProtocolBuilder httpProtocolBuilder) {
        this.httpProtocolBuilder = Objects.requireNonNull(httpProtocolBuilder);
    }

    @Override
    public HttpProtocolBuilder get() {
        return httpProtocolBuilder;
    }

    public HttpProtocolBuilderWrapper baseURL(String baseUrl) {
        return new HttpProtocolBuilderWrapper(httpProtocolBuilder.baseUrl(baseUrl));
    }

    public HttpProtocolBuilderWrapper acceptHeader(String acceptHeader) {
        return new HttpProtocolBuilderWrapper(httpProtocolBuilder.acceptHeader(Expression.of(acceptHeader)));
    }

    public HttpProtocolBuilderWrapper doNotTrackHeader(String doNotTrackHeader) {
        return new HttpProtocolBuilderWrapper(httpProtocolBuilder.doNotTrackHeader(Expression.of(doNotTrackHeader)));
    }

    public HttpProtocolBuilderWrapper acceptLanguageHeader(String acceptLanguageHeader) {
        return new HttpProtocolBuilderWrapper(httpProtocolBuilder.acceptLanguageHeader(Expression.of(acceptLanguageHeader)));
    }

    public HttpProtocolBuilderWrapper acceptEncodingHeader(String acceptEncodingHeader) {
        return new HttpProtocolBuilderWrapper(httpProtocolBuilder.acceptEncodingHeader(Expression.of(acceptEncodingHeader)));
    }

    public HttpProtocolBuilderWrapper userAgentHeader(String userAgentHeader) {
        return new HttpProtocolBuilderWrapper(httpProtocolBuilder.userAgentHeader(Expression.of(userAgentHeader)));
    }
}
