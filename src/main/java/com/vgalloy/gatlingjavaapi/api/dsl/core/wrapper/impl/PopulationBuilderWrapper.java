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

import io.gatling.core.structure.PopulationBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class PopulationBuilderWrapper implements Supplier<PopulationBuilder> {

  private final PopulationBuilder populationBuilder;

  public PopulationBuilderWrapper(PopulationBuilder populationBuilder) {
    this.populationBuilder = Objects.requireNonNull(populationBuilder);
  }

  @Override
  public PopulationBuilder get() {
    return populationBuilder;
  }
}
