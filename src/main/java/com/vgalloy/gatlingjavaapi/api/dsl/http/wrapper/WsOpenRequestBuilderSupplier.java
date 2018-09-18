package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.async.ws.WsOpenBuilder;
import io.gatling.http.request.builder.ws.WsOpenRequestBuilder;
import scala.Option;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsOpenRequestBuilderSupplier extends RequestBuilderWrapper<WsOpenRequestBuilder, WsOpenRequestBuilderSupplier> implements ActionBuilderSupplier {

    private final WsOpenRequestBuilder wsOpenRequestBuilder;

    public WsOpenRequestBuilderSupplier(WsOpenRequestBuilder wsOpenRequestBuilder) {
        this.wsOpenRequestBuilder = Objects.requireNonNull(wsOpenRequestBuilder);
    }

    @Override
    public WsOpenRequestBuilder get() {
        return wsOpenRequestBuilder;
    }

    @Override
    protected WsOpenRequestBuilderSupplier newInstance(WsOpenRequestBuilder newStructure) {
        return new WsOpenRequestBuilderSupplier(newStructure);
    }

    @Override
    public ActionBuilder toActionBuilder() {
        return new WsOpenBuilder(wsOpenRequestBuilder.commonAttributes().requestName(), wsOpenRequestBuilder.wsName(), wsOpenRequestBuilder, Option.empty());
    }
}
