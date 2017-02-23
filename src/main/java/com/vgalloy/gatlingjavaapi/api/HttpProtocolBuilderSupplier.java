package com.vgalloy.gatlingjavaapi.api;

import io.gatling.http.protocol.HttpProtocolBuilder;

import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface HttpProtocolBuilderSupplier extends Supplier<HttpProtocolBuilder> {

}
