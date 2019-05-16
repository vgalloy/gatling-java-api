package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.http.action.ws.WsConnectBuilder;
import io.gatling.http.check.ws.WsTextFrameCheck;
import io.gatling.http.request.builder.ws.WsConnectRequestBuilder;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.FiniteDuration;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsConnectRequestBuilderSupplier
    extends RequestBuilderWrapper<WsConnectRequestBuilder, WsConnectRequestBuilderSupplier>
    implements ActionBuilderSupplier {

  private final WsConnectRequestBuilder wsConnectRequestBuilder;

  public WsConnectRequestBuilderSupplier(WsConnectRequestBuilder wsConnectRequestBuilder) {
    this.wsConnectRequestBuilder = Objects.requireNonNull(wsConnectRequestBuilder);
  }

  @Override
  public WsConnectRequestBuilder get() {
    return wsConnectRequestBuilder;
  }

  @Override
  protected WsConnectRequestBuilderSupplier newInstance(WsConnectRequestBuilder newStructure) {
    return new WsConnectRequestBuilderSupplier(newStructure);
  }

  @Override
  public WsConnectBuilder toActionBuilder() {
    return WsConnectRequestBuilder.toActionBuilder(this.get());
  }

  public WsConnectRequestBuilderSupplier subprotocol(final String subprotocol) {
    Objects.requireNonNull(subprotocol, "subprotocol");
    return newInstance(get().subprotocol(subprotocol));
  }

  public WsConnectBuilderWrapper await(
      final long length, final TimeUnit unit, final WsTextFrameCheck wsTextFrameCheck) {
    final FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    final WsConnectBuilder newInstance =
        toActionBuilder().await(finiteDuration, ScalaHelper.map(wsTextFrameCheck));
    return new WsConnectBuilderWrapper(newInstance);
  }
}
