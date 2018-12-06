package com.vgalloy.gatlingjavaapi.api.dsl.http;

import io.gatling.http.check.ws.WsTextFrameCheck;
import java.util.Objects;

import io.gatling.core.check.DefaultFindCheckBuilder;
import io.gatling.core.session.SessionPrivateAttributes;
import io.gatling.http.Predef;
import io.gatling.http.action.ws.Ws;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.check.ws.WsBinaryFrameCheck;
import io.gatling.http.request.builder.Http;
import io.gatling.http.response.Response;

import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.DefaultFindCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.WsWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaHttpDsl {

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaHttpDsl() {
        throw new AssertionError("No instance of JavaHttpDsl");
    }

    public static HttpWrapper http(String requestName) {
        final Expression<String> requestNameExpression = Expression.of(requestName);
        return new HttpWrapper(new Http(requestNameExpression));
    }

    public static HttpProtocolBuilderWrapper http() {
        return new HttpProtocolBuilderWrapper(Predef.http(GatlingConfigurationSupplier.GATLING_CONFIGURATION));
    }

    public static WsWrapper ws(String requestName) {
        final Expression<String> requestNameExpression = Expression.of(requestName);
        final Ws ws = Predef.ws().apply(requestNameExpression, SessionPrivateAttributes.PrivateAttributePrefix() + "http.webSocket");
        return new WsWrapper(ws);
    }

    public static WsWrapper ws(String requestName, String wsName) {
        final Expression<String> requestNameExpression = Expression.of(requestName);
        return new WsWrapper(new Ws(requestNameExpression, wsName));
    }

    public static WsBinaryFrameCheck checkBinaryMessage(final String name) {
        Objects.requireNonNull(name, "name");
        return Predef.ws().checkBinaryMessage(name);
    }

    public static WsTextFrameCheck checkTextMessage(final String name) {
        Objects.requireNonNull(name, "name");
        return Predef.ws().checkTextMessage(name);
    }

    public static DefaultFindCheckBuilderWrapper status() {
        @SuppressWarnings("unchecked") final DefaultFindCheckBuilder<HttpCheck, Response, Integer> status = (DefaultFindCheckBuilder) Predef.status();
        return new DefaultFindCheckBuilderWrapper(status);
    }
}
