package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.ws.WsCloseBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsCloseBuilderWrapper
    implements Supplier<WsCloseBuilder>, ActionBuilderSupplier {

  private final WsCloseBuilder wsCloseBuilder;

  public WsCloseBuilderWrapper(WsCloseBuilder wsCloseBuilder) {
    this.wsCloseBuilder = Objects.requireNonNull(wsCloseBuilder);
  }

  @Override
  public WsCloseBuilder get() {
    return wsCloseBuilder;
  }

  @Override
  public ActionBuilder toActionBuilder() {
    return wsCloseBuilder;
  }
}
