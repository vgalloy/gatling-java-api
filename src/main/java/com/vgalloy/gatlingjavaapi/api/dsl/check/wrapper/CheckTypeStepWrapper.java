package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.Predef;
import io.gatling.http.check.async.AsyncCheckDSL;
import io.gatling.http.check.async.AsyncPlainCheckBuilder;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class CheckTypeStepWrapper {

    private final AsyncCheckDSL.CheckTypeStep checkTypeStep;

    public CheckTypeStepWrapper(AsyncCheckDSL.CheckTypeStep checkTypeStep) {
        this.checkTypeStep = Objects.requireNonNull(checkTypeStep);
    }

    public AsyncPlainCheckBuilderWrapper message() {
        return new AsyncPlainCheckBuilderWrapper(checkTypeStep.message());
    }

    public AsyncRegexCheckBuilderWrapper regex(String expression) {
        return new AsyncRegexCheckBuilderWrapper(checkTypeStep.regex(Expression.of(expression), Predef.defaultRegexExtractorFactory()));
    }

    public AsyncJsonPathCheckBuilderWrapper jsonPath(String path) {
        return new AsyncJsonPathCheckBuilderWrapper(checkTypeStep.jsonPath(Expression.of(path), Predef.defaultJsonPathExtractorFactory(), Predef.defaultJsonParsers()));
    }

    public AsyncJsonpJsonPathCheckBuilderWrapper jsonpJsonPath(String path) {
        return new AsyncJsonpJsonPathCheckBuilderWrapper(checkTypeStep.jsonpJsonPath(Expression.of(path), Predef.defaultJsonPathExtractorFactory(), Predef.defaultJsonParsers()));
    }
}
