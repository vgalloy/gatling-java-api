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
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.status;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import io.gatling.app.RunResult;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 08/04/17.
 *
 * @author Vincent Galloy
 */
final class DemoTest {

  private JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
  private JavaGatlingResultAnalyzer javaGatlingResultAnalyzer =
      JavaGatlingResultAnalyzer.getInstance();

  @Test
  void fatTest() {
    ScenarioBuilderWrapper scn =
        scenario("MyScenario")
            .exec(http("request_1").get("/home").check(status().toValidatorCheckBuilder().is(200)))
            .pause(1, TimeUnit.MILLISECONDS)
            .exec(
                http("request2")
                    .post("/post")
                    .headers(Collections.emptyMap())
                    .formParam("name", "value")
                    .check(status().toValidatorCheckBuilder().is(session -> 200)));
    HttpProtocolBuilderWrapper httpConf = http().baseURL("http://localhost:" + 8080);

    JavaSimulation javaSimulation =
        JavaSimulation.builder()
            .scenario(scn.inject(atOnceUsers(2)))
            .protocols(httpConf)
            .assertions(
                global().responseTime().max().lt(2),
                global().successfulRequests().percent().gt(105d))
            .build();

    RunResult runResult = javaGatlingRunner.run(javaSimulation);
    SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);

    // THEN
    javaGatlingResultAnalyzer.generateHtml(runResult);
    Assertions.assertFalse(simulationResult.isSuccess());
  }
}
