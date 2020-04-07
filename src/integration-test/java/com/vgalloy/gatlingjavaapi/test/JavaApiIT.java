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
import com.vgalloy.gatlingjavaapi.server.TestServerConfig;
import io.gatling.app.RunResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

/**
 * Created by Vincent Galloy on 27/02/2017.
 *
 * @author Vincent Galloy.
 */
@SpringBootTest(
    classes = TestServerConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JavaApiIT {

  @LocalServerPort private int serverPort;

  @Test
  void full() {
    JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
    JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

    ScenarioBuilderWrapper scn =
        scenario("MyScenario")
            .exec(http("request_1").get("/home"))
            .repeat(2, exec(http("request_get").get("/get/1")));
    HttpProtocolBuilderWrapper httpConf = http().baseURL("http://localhost:" + serverPort);

    JavaSimulation javaSimulation =
        JavaSimulation.builder()
            .scenario(scn.inject(atOnceUsers(2)))
            .protocols(httpConf)
            .assertions(
                global().responseTime().mean().lt(1_000),
                global().successfulRequests().percent().gt(99.9d))
            .build();

    RunResult runResult = javaGatlingRunner.run(javaSimulation);
    SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);

    // THEN
    javaGatlingResultAnalyzer.generateHtml(runResult);
    Assertions.assertTrue(simulationResult.isSuccess());
  }

  @Test
  public void assertHeader() {
    JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
    JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

    ScenarioBuilderWrapper scn =
        scenario("MyScenario")
            .exec(http("request_1").get("/hasHeader").header("testHeader", "value"));
    HttpProtocolBuilderWrapper httpConf = http().baseURL("http://localhost:" + serverPort);

    JavaSimulation javaSimulation =
        JavaSimulation.builder()
            .scenario(scn.inject(atOnceUsers(2)))
            .protocols(httpConf)
            .assertions(global().successfulRequests().percent().gt(99.9d))
            .build();

    RunResult runResult = javaGatlingRunner.run(javaSimulation);
    SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);

    // THEN
    javaGatlingResultAnalyzer.generateHtml(runResult);
    Assertions.assertTrue(simulationResult.isSuccess());
  }
}
