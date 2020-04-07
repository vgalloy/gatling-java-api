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

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class MultiExecutionSimulation extends SimulationWrapper {

  @Override
  protected void configure() {
    HttpProtocolBuilderWrapper httpConf =
        http()
            .baseURL("http://localhost:" + 9999)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ChainBuilderWrapper chain1 =
        exec(http("Home").get("/home"))
            .pause(500, TimeUnit.MILLISECONDS)
            .exec(http("Home").get("/home"));

    ChainBuilderWrapper chain2 =
        exec(http("Home").get("/home")).pause(1, TimeUnit.SECONDS).exec(http("Home").get("/home"));

    ScenarioBuilderWrapper scn = scenario("MyScenario").exec(chain1, chain2);

    setUp(scn.inject(atOnceUsers(2))).protocols(httpConf);
  }
}
