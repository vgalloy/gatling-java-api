package com.vgalloy.gatlingjavaapi.api.dsl.http;

import io.gatling.http.Predef;
import io.gatling.http.request.builder.Http;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.HttpWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expressions;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaHttpDSL {

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaHttpDSL() {
        throw new AssertionError();
    }

    public static HttpWrapper http(String requestName) {
        Expression<String> requestExpression = Expressions.of(requestName);
        return new HttpWrapper(new Http(requestExpression));
    }

    public static HttpProtocolBuilderWrapper http() {
        return new HttpProtocolBuilderWrapper(Predef.http(GatlingConfigurationSupplier.GATLING_CONFIGURATION));
    }
}
