package com.vgalloy.gatlingjavaapi.test;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import io.gatling.app.RunResult;
import org.junit.Assert;
import org.junit.Test;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaAssertionSupport.global;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDSL.http;

/**
 * Created by Vincent Galloy on 08/04/17.
 *
 * @author Vincent Galloy
 */
public final class DemoTest {

    private JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
    private JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

    @Test
    public void fatTest() {
        ScenarioBuilderWrapper scn = scenario("MyScenario")
            .exec(http("request_1")
                .get("/home"))
            .pause(1, TimeUnit.MILLISECONDS)
            .exec(http("request2")
                .post("/post")
                .headers(Collections.emptyMap())
                .formParam("name", "value"));
//                .check(status.is(session -> 200)));
        HttpProtocolBuilderWrapper httpConf = http()
            .baseURL("http://localhost:" + 8080);

        JavaSimulation javaSimulation = JavaSimulation.builder()
            .scenario(
                scn.inject(atOnceUsers(2))
            )
            .protocols(httpConf)
            .assertion(
                global().responseTime().max().lt(2),
                global().successfulRequests().percent().gt(105d)
            )
            .build();

        RunResult runResult = javaGatlingRunner.run(javaSimulation);
        SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);

        // THEN
        javaGatlingResultAnalyzer.generateHtml(runResult);
        Assert.assertFalse(simulationResult.isSuccess());
    }
}
