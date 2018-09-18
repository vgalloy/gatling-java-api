package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.core.check.CheckBuilder;
import io.gatling.http.action.async.ws.WsSend;
import io.gatling.http.action.async.ws.WsSendBuilder;
import io.gatling.http.check.async.AsyncCheck;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsSendBuilderWrapper implements Supplier<WsSendBuilder>, ActionBuilderSupplier {

    private final WsSendBuilder wsSendBuilder;

    public WsSendBuilderWrapper(WsSendBuilder wsSendBuilder) {
        this.wsSendBuilder = Objects.requireNonNull(wsSendBuilder);
    }

    @Override
    public WsSendBuilder get() {
        return wsSendBuilder;
    }

    @Override
    public ActionBuilder toActionBuilder() {
        return wsSendBuilder;
    }
//
//    public WsSendBuilderWrapper check(CheckBuilder<AsyncCheck, String, ?, ?> checkBuilder) {
//        return new WsSendBuilderWrapper(get().check(checkBuilder));
//    }
}
