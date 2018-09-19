package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.AsyncPlainCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.CheckBuilder;
import io.gatling.http.check.async.AsyncCheck;
import io.gatling.http.request.builder.ws.Ws;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsWrapper implements Supplier<Ws> {

    private final Ws ws;

    public WsWrapper(Ws ws) {
        this.ws = Objects.requireNonNull(ws);
    }

    @Override
    public Ws get() {
        return ws;
    }

    public WsWrapper wsName(String wsName) {
        return new WsWrapper(ws.wsName(wsName));
    }

    public WsOpenRequestBuilderSupplier open(String url) {
        return new WsOpenRequestBuilderSupplier(ws.open(Expression.of(url)));
    }

    public WsSendBuilderWrapper sendBytes(byte[] bytes) {
        return new WsSendBuilderWrapper(ws.sendBytes(Expression.of(bytes)));
    }

    public WsSendBuilderWrapper sendText(String text) {
        return new WsSendBuilderWrapper(ws.sendText(Expression.of(text)));
    }

    public WsSetCheckBuilderWrapper check(CheckBuilder<AsyncCheck, String, ?, ?> javaAsyncCheckBuilder) {
        return new WsSetCheckBuilderWrapper(ws.check(javaAsyncCheckBuilder));
    }

    public WsSetCheckBuilderWrapper check(AsyncPlainCheckBuilderWrapper asyncPlainCheckBuilderWrapper) {
        return check(asyncPlainCheckBuilderWrapper.toCheckBuilder());
    }

    public WsCancelCheckBuilderWrapper cancelCheck() {
        return new WsCancelCheckBuilderWrapper(ws.cancelCheck());
    }

    public WsReconciliateBuilderWrapper reconciliate() {
        return new WsReconciliateBuilderWrapper(ws.reconciliate());
    }

    public WsCloseBuilderWrapper close() {
        return new WsCloseBuilderWrapper(ws.close());
    }

}
