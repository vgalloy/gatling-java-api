/*
 * Copyright 2020 Vincent Galloy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
