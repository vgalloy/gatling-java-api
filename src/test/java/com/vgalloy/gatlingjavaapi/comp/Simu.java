package com.vgalloy.gatlingjavaapi.comp;

import com.vgalloy.gatlingjavaapi.internal.Expression;
import com.vgalloy.gatlingjavaapi.internal.Helper;
import io.gatling.app.CustomSimulation;
import io.gatling.core.Predef;
import io.gatling.core.structure.PopulationBuilder;
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
public class Simu extends CustomSimulation {

	public static int port = 8082;

	@Override
	protected PopulationBuilder populationBuilder() {
		Expression<String> requestName = Helper.buildExpressionFromString("request_1");
		Expression<String> requestUrl = Helper.buildExpressionFromString("/home");

		HttpRequestBuilder httpRequestBuilder = new io.gatling.http.request.builder.Http(requestName).get(requestUrl);
		HttpRequestActionBuilder httpRequestActionBuilder = new HttpRequestActionBuilder(httpRequestBuilder);

		ScenarioBuilder scenarioBuilder = new ScenarioBuilder("MyScenario", List$.MODULE$.empty());
		// DON'T REMOVE THE CAST
		ScenarioBuilder result = (ScenarioBuilder) scenarioBuilder.exec(httpRequestActionBuilder);

		return result.inject(Helper.map(Predef.atOnceUsers(1)));
	}

	@Override
	protected HttpProtocolBuilder httpProtocolBuilder() {
		return io.gatling.http.Predef.http(Predef.configuration())
				.baseURL("http://localhost:" + port)
				.acceptHeader(Helper.buildExpressionFromString("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8"))
				.doNotTrackHeader(Helper.buildExpressionFromString("1"))
				.acceptLanguageHeader(Helper.buildExpressionFromString("en-US,en;q=0.5"))
				.acceptEncodingHeader(Helper.buildExpressionFromString("gzip, deflate"))
				.userAgentHeader(Helper.buildExpressionFromString("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0"));
	}
}
