package com.vgalloy.gatlingjavaapi.io.gatling.app;

import com.vgalloy.gatlingjavaapi.internal.Expression;
import com.vgalloy.gatlingjavaapi.internal.Helper;
import io.gatling.core.Predef;
import io.gatling.core.scenario.Simulation;
import io.gatling.core.structure.ScenarioBuilder;
import io.gatling.http.action.sync.HttpRequestActionBuilder;
import io.gatling.http.protocol.HttpProtocolBuilder;
import io.gatling.http.request.builder.HttpRequestBuilder;
import scala.collection.immutable.List$;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class MySim extends Simulation {

	public MySim() {
		HttpProtocolBuilder httpProtocolBuilder = io.gatling.http.Predef.http(Predef.configuration())
				.baseURL("http://localhost:8082/")
				.acceptHeader(Helper.buildExpressionFromString("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"))
				.doNotTrackHeader(Helper.buildExpressionFromString("1"))
				.acceptLanguageHeader(Helper.buildExpressionFromString("en-US,en;q=0.5"))
				.acceptEncodingHeader(Helper.buildExpressionFromString("gzip, deflate"))
				.userAgentHeader(Helper.buildExpressionFromString("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0"));

		Expression<String> requestName = Helper.buildExpressionFromString("request_1");
		Expression<String> requestUrl = Helper.buildExpressionFromString("/");

		HttpRequestBuilder httpRequestBuilder = new io.gatling.http.request.builder.Http(requestName).get(requestUrl);
		HttpRequestActionBuilder httpRequestActionBuilder = new HttpRequestActionBuilder(httpRequestBuilder);

		ScenarioBuilder scenarioBuilder = new ScenarioBuilder("MyScenario", List$.MODULE$.empty());
		// DON'T REMOVE THE CAST
		ScenarioBuilder result = (ScenarioBuilder) scenarioBuilder.exec(httpRequestActionBuilder);

		setUp(
				Helper.map(result.inject(Helper.map(Predef.atOnceUsers(1))))
		).protocols(Helper.map(httpProtocolBuilder.build()));
	}
}
