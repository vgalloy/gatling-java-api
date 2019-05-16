package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.ws.WsSendBinaryFrameBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsSendBinaryFrameBuilderWrapper
    implements Supplier<WsSendBinaryFrameBuilder>, ActionBuilderSupplier {

  private final WsSendBinaryFrameBuilder wsSendBinaryFrameBuilder;

  public WsSendBinaryFrameBuilderWrapper(WsSendBinaryFrameBuilder wsSendBinaryFrameBuilder) {
    this.wsSendBinaryFrameBuilder = Objects.requireNonNull(wsSendBinaryFrameBuilder);
  }

  @Override
  public WsSendBinaryFrameBuilder get() {
    return wsSendBinaryFrameBuilder;
  }

  @Override
  public ActionBuilder toActionBuilder() {
    return wsSendBinaryFrameBuilder;
  }

  //    public WsSendBinaryFrameBuilderWrapper check(CheckBuilder<AsyncCheck, String, ?, ?>
  // checkBuilder) {
  //        return new WsSendBinaryFrameBuilderWrapper(get().check(checkBuilder));
  //    }
  //
  //    public WsSendBinaryFrameBuilderWrapper check(AsyncPlainCheckBuilderWrapper
  // asyncPlainCheckBuilderWrapper) {
  //        return check(asyncPlainCheckBuilderWrapper.toCheckBuilder());
  //    }
  //
  //    public WsSendBinaryFrameBuilderWrapper check(CheckTypeStepWrapper checkTypeStepWrapper) {
  //        return check(checkTypeStepWrapper.message().find().get().exists());
  //    }
}
