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
