package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.simulation.AssertionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultiExecutionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultiScenariosSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultipleInjectionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.SimpleSimulation;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class SimulationDslTest {

	@Test
	public void simple() {
		JavaGatlingRunner.getInstance().run(SimpleSimulation.class);
	}

	@Test
	public void multiScenario() {
		JavaGatlingRunner.getInstance().run(MultiScenariosSimulation.class);
	}

	@Test
	public void assertion() {
		JavaGatlingRunner.getInstance().run(AssertionSimulation.class);
	}

	@Test
	public void multiExecution() {
		JavaGatlingRunner.getInstance().run(MultiExecutionSimulation.class);
	}

	@Test
	public void MultipleInjection() {
		JavaGatlingRunner.getInstance().run(MultipleInjectionSimulation.class);
	}
}
