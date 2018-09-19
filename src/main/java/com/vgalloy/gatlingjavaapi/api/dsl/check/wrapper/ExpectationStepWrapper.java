package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.http.check.async.AsyncCheckDSL;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class ExpectationStepWrapper {

    private final AsyncCheckDSL.ExpectationStep expectationStep;

    public ExpectationStepWrapper(AsyncCheckDSL.ExpectationStep expectationStep) {
        this.expectationStep = Objects.requireNonNull(expectationStep);
    }

    public CheckTypeStepWrapper expect(int count) {
        return new CheckTypeStepWrapper(expectationStep.expect(count));
    }

    public CheckTypeStepWrapper until(int count) {
        return new CheckTypeStepWrapper(expectationStep.until(count));
    }
}
