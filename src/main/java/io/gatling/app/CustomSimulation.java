package io.gatling.app;

import com.vgalloy.gatlingjavaapi.internal.Helper;
import io.gatling.core.scenario.Simulation;
import io.gatling.core.structure.PopulationBuilder;
import io.gatling.http.protocol.HttpProtocolBuilder;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public abstract class CustomSimulation extends Simulation {

	protected abstract PopulationBuilder populationBuilder();

	protected abstract HttpProtocolBuilder httpProtocolBuilder();

	public CustomSimulation() {
		PopulationBuilder populationBuilder = populationBuilder();
		HttpProtocolBuilder httpProtocolBuilder = httpProtocolBuilder();

		setUp(
				Helper.map(populationBuilder)
		).protocols(Helper.map(httpProtocolBuilder.build()));
	}
}
