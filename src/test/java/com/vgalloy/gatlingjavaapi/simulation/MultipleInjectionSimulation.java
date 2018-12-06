package com.vgalloy.gatlingjavaapi.simulation;

import java.util.concurrent.TimeUnit;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.nothingFor;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.rampUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class MultipleInjectionSimulation extends SimulationWrapper {

    @Override
    protected void configure() {
        HttpProtocolBuilderWrapper httpConf = http()
            .baseURL("http://localhost:" + 9999);

        ScenarioBuilderWrapper scn = scenario("MyScenario")
            .exec(http("request_1")
                .get("/home"));

        setUp(
            scn.inject(
                atOnceUsers(10),
                nothingFor(100, TimeUnit.MICROSECONDS),
                rampUsers(10, 1, TimeUnit.SECONDS)
            )
        ).protocols(httpConf);
    }
}
