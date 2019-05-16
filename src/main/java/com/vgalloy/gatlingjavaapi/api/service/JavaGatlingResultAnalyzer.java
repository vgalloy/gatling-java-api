package com.vgalloy.gatlingjavaapi.api.service;

import com.vgalloy.gatlingjavaapi.internal.impl.JavaGatlingResultAnalyzerImpl;
import io.gatling.app.RunResult;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface JavaGatlingResultAnalyzer {

  static JavaGatlingResultAnalyzer getInstance() {
    return JavaGatlingResultAnalyzerImpl.INSTANCE;
  }

  SimulationResult load(RunResult runResult);

  void generateHtml(RunResult runResult);
}
