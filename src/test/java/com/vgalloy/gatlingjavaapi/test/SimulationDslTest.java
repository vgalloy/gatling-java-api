package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.simulation.AssertionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultiExecutionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultiScenariosSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultipleInjectionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.SimpleSimulation;
import com.vgalloy.gatlingjavaapi.simulation.WsSimulation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
final class SimulationDslTest {

  @Test
  void simple() {
    JavaGatlingRunner.getInstance().run(SimpleSimulation.class);
  }

  @Test
  void multiScenario() {
    JavaGatlingRunner.getInstance().run(MultiScenariosSimulation.class);
  }

  @Test
  void assertion() {
    JavaGatlingRunner.getInstance().run(AssertionSimulation.class);
  }

  @Test
  void multiExecution() {
    JavaGatlingRunner.getInstance().run(MultiExecutionSimulation.class);
  }

  @Test
  void multipleInjection() {
    JavaGatlingRunner.getInstance().run(MultipleInjectionSimulation.class);
  }

  @Test
  @Disabled
  void wsSimulation() {
    JavaGatlingRunner.getInstance().run(WsSimulation.class);
  }
}
