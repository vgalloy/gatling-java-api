/*
 * Copyright 2020 Vincent Galloy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vgalloy.gatlingjavaapi.internal.impl;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import io.gatling.app.RunResult;
import io.gatling.app.RunResultProcessor;
import io.gatling.charts.stats.LogFileReader;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public enum JavaGatlingResultAnalyzerImpl implements JavaGatlingResultAnalyzer {
  INSTANCE;

  @Override
  public SimulationResult load(RunResult runResult) {
    return new SimulationResult(
        new LogFileReader(runResult.runId(), GatlingConfigurationSupplier.GATLING_CONFIGURATION));
  }

  @Override
  public void generateHtml(RunResult runResult) {
    RunResultProcessor runResultProcessor =
        new RunResultProcessor(GatlingConfigurationSupplier.GATLING_CONFIGURATION);
    runResultProcessor.processRunResult(runResult);
  }
}
