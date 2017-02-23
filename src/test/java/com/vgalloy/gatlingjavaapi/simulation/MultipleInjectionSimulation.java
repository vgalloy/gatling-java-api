package com.vgalloy.gatlingjavaapi.simulation;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;

import java.util.concurrent.TimeUnit;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.nothingFor;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.rampUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDSL.http;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class MultipleInjectionSimulation extends SimulationWrapper {

	public static int port = 8082;

	@Override
	protected void configure() {
		HttpProtocolBuilderWrapper httpConf = http()
				.baseURL("http://localhost:" + port);

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
