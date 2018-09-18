package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.async.ws.WsCancelCheckBuilder;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsCancelCheckBuilderWrapper implements Supplier<WsCancelCheckBuilder>, ActionBuilderSupplier {

    private final WsCancelCheckBuilder wsCancelCheckBuilder;

    public WsCancelCheckBuilderWrapper(WsCancelCheckBuilder wsCancelCheckBuilder) {
        this.wsCancelCheckBuilder = Objects.requireNonNull(wsCancelCheckBuilder);
    }

    @Override
    public WsCancelCheckBuilder get() {
        return wsCancelCheckBuilder;
    }

    @Override
    public ActionBuilder toActionBuilder() {
        return wsCancelCheckBuilder;
    }
}
