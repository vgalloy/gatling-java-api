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
		return new SimulationResult(new LogFileReader(runResult.runId(), GatlingConfigurationSupplier.GATLING_CONFIGURATION));
	}

	@Override
	public void generateHtml(RunResult runResult) {
		RunResultProcessor runResultProcessor = new RunResultProcessor(GatlingConfigurationSupplier.GATLING_CONFIGURATION);
		runResultProcessor.processRunResult(runResult);
	}
}
