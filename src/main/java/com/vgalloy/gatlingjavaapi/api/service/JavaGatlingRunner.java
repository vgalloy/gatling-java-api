package com.vgalloy.gatlingjavaapi.api.service;

import com.vgalloy.gatlingjavaapi.internal.impl.JavaGatlingRunnerImpl;
import io.gatling.app.RunResult;
import io.gatling.core.scenario.Simulation;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface JavaGatlingRunner {

  static JavaGatlingRunner getInstance() {
    return JavaGatlingRunnerImpl.INSTANCE;
  }

  RunResult run(Class<? extends Simulation> simulationClass);

  RunResult run(JavaSimulation javaSimulation);
}
