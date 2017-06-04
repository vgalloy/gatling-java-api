package com.vgalloy.gatlingjavaapi.api.service;

import java.util.List;
import java.util.Objects;

import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.charts.stats.LogFileReader;
import io.gatling.charts.stats.ResultsHolder;
import io.gatling.commons.stats.assertion.AssertionResult;
import io.gatling.commons.stats.assertion.AssertionValidator;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class SimulationResult {

    private final LogFileReader logFileReader;

    public SimulationResult(LogFileReader logFileReader) {
        this.logFileReader = Objects.requireNonNull(logFileReader);
    }

    public List<AssertionResult> getAssertionResult() {
        return ScalaHelper.map(AssertionValidator.validateAssertions(logFileReader));
    }

    public ResultsHolder getResultsHolder() {
        return logFileReader.resultsHolder();
    }

    public boolean isSuccess() {
        return getAssertionResult().stream()
            .map(AssertionResult::result)
            .filter(e -> !e)
            .findFirst()
            .orElse(true);
    }
}
