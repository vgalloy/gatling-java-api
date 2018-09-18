package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.async.ws.WsCancelCheckBuilder;
import io.gatling.http.action.async.ws.WsReconciliateBuilder;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsReconciliateBuilderWrapper implements Supplier<WsReconciliateBuilder>, ActionBuilderSupplier {

    private final WsReconciliateBuilder wsReconciliateBuilder;

    public WsReconciliateBuilderWrapper(WsReconciliateBuilder wsCancelCheckBuilder) {
        this.wsReconciliateBuilder = Objects.requireNonNull(wsCancelCheckBuilder);
    }

    @Override
    public WsReconciliateBuilder get() {
        return wsReconciliateBuilder;
    }

    @Override
    public ActionBuilder toActionBuilder() {
        return wsReconciliateBuilder;
    }
}
