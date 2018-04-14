package com.vgalloy.gatlingjavaapi.test;

import io.gatling.app.RunResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import com.vgalloy.gatlingjavaapi.server.TestServerConfig;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaAssertionSupport.global;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDSL.http;

/**
 * Created by Vincent Galloy on 27/02/2017.
 *
 * @author Vincent Galloy.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestServerConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JavaApiIT {

    @LocalServerPort
    private int serverPort;

    @Test
    public void full() {
        JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
        JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

        ScenarioBuilderWrapper scn = scenario("MyScenario")
            .exec(http("request_1")
                .get("/home"))
            .repeat(2,
                exec(http("request_get")
                .get("/get/1")
            ));
        HttpProtocolBuilderWrapper httpConf = http()
            .baseURL("http://localhost:" + serverPort);

        JavaSimulation javaSimulation = JavaSimulation.builder()
            .scenario(
                scn.inject(atOnceUsers(2))
            )
            .protocols(httpConf)
            .assertion(
                global().responseTime().mean().lt(1_000),
                global().successfulRequests().percent().gt(99.9d)
            )
            .build();

        RunResult runResult = javaGatlingRunner.run(javaSimulation);
        SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);

        // THEN
        javaGatlingResultAnalyzer.generateHtml(runResult);
        Assert.assertTrue(simulationResult.isSuccess());
    }
}
