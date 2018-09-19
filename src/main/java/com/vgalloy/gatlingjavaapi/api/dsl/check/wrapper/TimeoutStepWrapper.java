package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.http.check.async.AsyncCheckDSL;
import scala.concurrent.duration.Duration;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class TimeoutStepWrapper {

    private final AsyncCheckDSL.TimeoutStep timeoutStep;

    public TimeoutStepWrapper(AsyncCheckDSL.TimeoutStep timeoutStep) {
        this.timeoutStep = Objects.requireNonNull(timeoutStep);
    }

    public ExpectationStepWrapper within(int length, TimeUnit unit) {
        return new ExpectationStepWrapper(timeoutStep.within(Duration.apply(length, unit)));
    }
}
