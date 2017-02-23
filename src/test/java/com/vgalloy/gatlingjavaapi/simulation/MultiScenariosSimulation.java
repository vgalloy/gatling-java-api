package com.vgalloy.gatlingjavaapi.simulation;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDSL.http;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class MultiScenariosSimulation extends SimulationWrapper {

	public static int port = 8082;

	@Override
	protected void configure() {
		HttpProtocolBuilderWrapper httpConf = http()
				.baseURL("http://localhost:" + port)
				.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
				.doNotTrackHeader("1")
				.acceptLanguageHeader("en-US,en;q=0.5")
				.acceptEncodingHeader("gzip, deflate")
				.userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

		ScenarioBuilderWrapper scn = scenario("MyScenario")
				.exec(http("request_1")
						.get("/home"));

		ScenarioBuilderWrapper scn2 = scenario("MyScenario2")
				.exec(http("request_2")
						.get("/home"));

		setUp(
				scn.inject(atOnceUsers(10)),
				scn2.inject(atOnceUsers(10))
		)
				.protocols(httpConf);
	}
}
