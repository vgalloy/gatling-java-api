package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import com.vgalloy.gatlingjavaapi.server.TestServerConfig;
import io.gatling.app.RunResult;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaAssertionSupport.global;
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
public class JavaApiTest {

	@LocalServerPort
	private int serverPort;

	@Test
	public void full() {
		JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
		JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

		ScenarioBuilderWrapper scn = scenario("MyScenario")
				.exec(http("request_1")
						.get("/home"));
		HttpProtocolBuilderWrapper httpConf = http()
				.baseURL("http://localhost:" + serverPort);

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
