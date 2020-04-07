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
package com.vgalloy.gatlingjavaapi.api.dsl.loop;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait.ExecsWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.Predef;
import io.gatling.core.structure.ChainBuilder;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaLoopSupport {

  /** Constructor. To prevent external instantiation */
  private JavaLoopSupport() {
    throw new AssertionError("No instance of JavaLoopSupport");
  }

  public static ChainBuilderWrapper repeat(
      int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    return repeat(times, UUID.randomUUID().toString(), execsWrapper);
  }

  public static ChainBuilderWrapper repeat(
      int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    Objects.requireNonNull(counterName);
    Objects.requireNonNull(execsWrapper);

    return new ChainBuilderWrapper(
        (ChainBuilder) Predef.repeat(Expression.of(times), counterName, execsWrapper.get()));
  }
}
