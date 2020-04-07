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
package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.PopulationBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.commons.stats.assertion.Assertion;
import io.gatling.core.protocol.Protocol;
import io.gatling.core.scenario.Simulation;
import io.gatling.core.structure.PopulationBuilder;
import io.gatling.http.protocol.HttpProtocolBuilder;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public abstract class SimulationWrapper extends Simulation {

  public SimulationWrapper() {
    configure();
  }

  protected abstract void configure();

  protected SetUpWrapper setUp(PopulationBuilderWrapper... populationBuilderWrappers) {
    Objects.requireNonNull(populationBuilderWrappers);

    scala.collection.immutable.List<PopulationBuilder> result =
        Arrays.stream(populationBuilderWrappers)
            .map(PopulationBuilderWrapper::get)
            .collect(ScalaHelper.toScalaList());
    return new SetUpWrapper(setUp(result));
  }

  protected final class SetUpWrapper {

    private final SetUp setUp;

    private SetUpWrapper(SetUp setUp) {
      this.setUp = setUp;
    }

    public SetUpWrapper protocols(HttpProtocolBuilderWrapper... httpProtocolBuilderWrapper) {
      Objects.requireNonNull(httpProtocolBuilderWrapper);

      scala.collection.immutable.List<Protocol> result =
          Arrays.stream(httpProtocolBuilderWrapper)
              .map(HttpProtocolBuilderWrapper::get)
              .map(HttpProtocolBuilder::build)
              .collect(ScalaHelper.toScalaList());
      return new SetUpWrapper(setUp.protocols(result));
    }

    public SetUpWrapper assertion(Assertion... assertions) {
      Objects.requireNonNull(assertions);

      return new SetUpWrapper(setUp.assertions(ScalaHelper.map(assertions)));
    }
  }
}
