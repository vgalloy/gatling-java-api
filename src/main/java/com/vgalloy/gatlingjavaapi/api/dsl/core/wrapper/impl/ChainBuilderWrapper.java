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
package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait.StructureBuilderWrapper;
import io.gatling.core.structure.ChainBuilder;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ChainBuilderWrapper
    implements StructureBuilderWrapper<ChainBuilder, ChainBuilderWrapper> {

  private final ChainBuilder chainBuilder;

  public ChainBuilderWrapper(ChainBuilder chainBuilder) {
    this.chainBuilder = Objects.requireNonNull(chainBuilder);
  }

  @Override
  public ChainBuilderWrapper newInstance(ChainBuilder newStructure) {
    return new ChainBuilderWrapper(newStructure);
  }

  @Override
  public ChainBuilder get() {
    return chainBuilder;
  }
}
