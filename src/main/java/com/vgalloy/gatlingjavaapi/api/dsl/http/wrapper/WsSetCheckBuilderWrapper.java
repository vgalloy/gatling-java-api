package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.core.check.CheckBuilder;
import io.gatling.http.action.async.ws.WsSendBuilder;
import io.gatling.http.action.async.ws.WsSetCheckBuilder;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsSetCheckBuilderWrapper implements Supplier<WsSetCheckBuilder>, ActionBuilderSupplier {

    private final WsSetCheckBuilder wsSetCheckBuilder;

    public WsSetCheckBuilderWrapper(WsSetCheckBuilder wsSetCheckBuilder) {
        this.wsSetCheckBuilder = Objects.requireNonNull(wsSetCheckBuilder);
    }

    @Override
    public WsSetCheckBuilder get() {
        return wsSetCheckBuilder;
    }

    @Override
    public ActionBuilder toActionBuilder() {
        return wsSetCheckBuilder;
    }

}
