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
package com.vgalloy.gatlingjavaapi.simulation;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.nothingFor;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.rampUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class MultipleInjectionSimulation extends SimulationWrapper {

  @Override
  protected void configure() {
    HttpProtocolBuilderWrapper httpConf = http().baseURL("http://localhost:" + 9999);

    ScenarioBuilderWrapper scn = scenario("MyScenario").exec(http("request_1").get("/home"));

    setUp(
            scn.inject(
                atOnceUsers(10),
                nothingFor(100, TimeUnit.MICROSECONDS),
                rampUsers(10, 1, TimeUnit.SECONDS)))
        .protocols(httpConf);
  }
}
