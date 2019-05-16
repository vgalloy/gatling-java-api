package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.ws.WsSendTextFrameBuilder;
import io.gatling.http.check.ws.WsTextFrameCheck;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import scala.concurrent.duration.FiniteDuration;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsSendTextFrameBuilderWrapper
    implements Supplier<WsSendTextFrameBuilder>, ActionBuilderSupplier {

  private final WsSendTextFrameBuilder wsSendTextFrameBuilder;

  public WsSendTextFrameBuilderWrapper(WsSendTextFrameBuilder wsSendTextFrameBuilder) {
    this.wsSendTextFrameBuilder = Objects.requireNonNull(wsSendTextFrameBuilder);
  }

  @Override
  public WsSendTextFrameBuilder get() {
    return wsSendTextFrameBuilder;
  }

  @Override
  public ActionBuilder toActionBuilder() {
    return wsSendTextFrameBuilder;
  }

  public WsSendTextFrameBuilderWrapper await(
      long length, TimeUnit unit, final WsTextFrameCheck... wsTextFrameCheck) {
    final FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    final WsSendTextFrameBuilder newInstance =
        get().await(finiteDuration, ScalaHelper.map(wsTextFrameCheck));
    return new WsSendTextFrameBuilderWrapper(newInstance);
  }
}
