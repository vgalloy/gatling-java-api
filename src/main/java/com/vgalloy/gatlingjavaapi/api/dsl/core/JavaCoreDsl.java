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
package com.vgalloy.gatlingjavaapi.api.dsl.core;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import io.gatling.core.Predef;
import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.ScenarioBuilder;
import scala.collection.immutable.List$;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaCoreDsl {

  /** Constructor. To prevent instantiation */
  private JavaCoreDsl() {
    throw new AssertionError("No instance of JavaCoreDsl");
  }

  public static ScenarioBuilderWrapper scenario(String scenarioName) {
    return new ScenarioBuilderWrapper(new ScenarioBuilder(scenarioName, List$.MODULE$.empty()));
  }

  public static ChainBuilderWrapper exec(ActionBuilderSupplier actionBuilderSupplier) {
    return new ChainBuilderWrapper(
        (ChainBuilder) Predef.exec(actionBuilderSupplier.toActionBuilder()));
  }
}
