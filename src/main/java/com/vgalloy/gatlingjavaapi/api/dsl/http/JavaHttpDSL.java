package com.vgalloy.gatlingjavaapi.api.dsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.WsWrapper;
import io.gatling.core.check.DefaultFindCheckBuilder;
import io.gatling.http.HttpDsl;
import io.gatling.http.Predef;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.request.builder.Http;

import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.DefaultFindCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.http.request.builder.ws.Ws;
import io.gatling.http.response.Response;

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
        final Expression<String> requestNameExpression = Expression.of(requestName);
        return new HttpWrapper(new Http(requestNameExpression));
    }

    public static HttpProtocolBuilderWrapper http() {
        return new HttpProtocolBuilderWrapper(Predef.http(GatlingConfigurationSupplier.GATLING_CONFIGURATION));
    }

    public static WsWrapper ws(String requestName) {
        return ws(requestName, Ws.DefaultWebSocketName());
    }

    public static WsWrapper ws(String requestName, String wsName) {
        final Expression<String> requestNameExpression = Expression.of(requestName);
        return new WsWrapper(new Ws(requestNameExpression, wsName));
    }

    public static DefaultFindCheckBuilderWrapper status() {
        @SuppressWarnings("unchecked")
        final DefaultFindCheckBuilder<HttpCheck, Response, Response, Integer> status = (DefaultFindCheckBuilder) Predef.status();
        return new DefaultFindCheckBuilderWrapper(status);
    }
}
