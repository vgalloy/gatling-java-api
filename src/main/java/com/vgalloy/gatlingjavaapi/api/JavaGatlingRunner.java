package com.vgalloy.gatlingjavaapi.api;

import com.vgalloy.gatlingjavaapi.internal.JavaGatlingRunnerImpl;
import io.gatling.core.scenario.Simulation;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface JavaGatlingRunner {

	void run(Class<? extends Simulation> simulationClass);

	static JavaGatlingRunner getRunner() {
		return JavaGatlingRunnerImpl.INSTANCE;
	}
}
