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
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.core.Predef;
import io.gatling.core.controller.inject.open.OpenInjectionStep;
import io.gatling.core.structure.ScenarioBuilder;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ScenarioBuilderWrapper
    implements StructureBuilderWrapper<ScenarioBuilder, ScenarioBuilderWrapper> {

  private final ScenarioBuilder scenarioBuilder;

  public ScenarioBuilderWrapper(ScenarioBuilder scenarioBuilder) {
    this.scenarioBuilder = Objects.requireNonNull(scenarioBuilder);
  }

  @Override
  public ScenarioBuilderWrapper newInstance(ScenarioBuilder newStructure) {
    return new ScenarioBuilderWrapper(newStructure);
  }

  @Override
  public ScenarioBuilder get() {
    return scenarioBuilder;
  }

  public PopulationBuilderWrapper inject(
      OpenInjectionStep injectionProfileFactory, OpenInjectionStep... injectionProfileFactories) {
    Objects.requireNonNull(injectionProfileFactories);
    return new PopulationBuilderWrapper(
        scenarioBuilder.inject(
            injectionProfileFactory,
            ScalaHelper.map(injectionProfileFactories),
            Predef.openInjectionProfileFactory()));
  }
}
