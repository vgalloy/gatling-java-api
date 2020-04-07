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
package com.vgalloy.gatlingjavaapi.test;

import static com.vgalloy.gatlingjavaapi.api.dsl.assertion.JavaAssertionSupport.global;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import io.gatling.app.RunResult;

public final class Main {

  public static void main(String[] args) {
    JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
    JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

    ScenarioBuilderWrapper scn =
        scenario("MyScenario")
            .exec(http("request_1").get("/home"))
            .repeat(2, exec(http("request_get").get("/get/1")));
    HttpProtocolBuilderWrapper httpConf = http().baseURL("http://localhost:" + 8080);

    JavaSimulation javaSimulation =
        JavaSimulation.builder()
            .scenario(scn.inject(atOnceUsers(2)))
            .protocols(httpConf)
            .assertions(
                global().responseTime().mean().lt(1_000),
                global().successfulRequests().percent().gt(99.9d))
            .build();

    // Run the simulation
    RunResult runResult = javaGatlingRunner.run(javaSimulation);

    // Assert everything is ok
    SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);
    if (!simulationResult.isSuccess()) {
      throw new IllegalStateException("Run fails");
    }

    // Generate HTML report
    javaGatlingResultAnalyzer.generateHtml(runResult);
  }
}
