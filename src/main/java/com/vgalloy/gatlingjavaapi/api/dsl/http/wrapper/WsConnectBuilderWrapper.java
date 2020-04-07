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
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.http.action.ws.WsConnectBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 2/9/19.
 *
 * @author Vincent Galloy
 */
public class WsConnectBuilderWrapper implements Supplier<WsConnectBuilder>, ActionBuilderSupplier {

  private final WsConnectBuilder wsConnectBuilder;

  public WsConnectBuilderWrapper(final WsConnectBuilder wsConnectBuilder) {
    this.wsConnectBuilder = Objects.requireNonNull(wsConnectBuilder, "wsConnectBuilder");
  }

  @Override
  public WsConnectBuilder get() {
    return wsConnectBuilder;
  }

  public WsConnectBuilderWrapper onConnected(final ChainBuilderWrapper chainBuilderWrapper) {
    return new WsConnectBuilderWrapper(wsConnectBuilder.onConnected(chainBuilderWrapper.get()));
  }

  @Override
  public ActionBuilder toActionBuilder() {
    return wsConnectBuilder;
  }
}
