package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.simulation.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class SimulationDslTest {

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
  public void multipleInjection() {
    JavaGatlingRunner.getInstance().run(MultipleInjectionSimulation.class);
  }

  @Test
  @Ignore
  public void wsSimulation() {
    JavaGatlingRunner.getInstance().run(WsSimulation.class);
  }
}
