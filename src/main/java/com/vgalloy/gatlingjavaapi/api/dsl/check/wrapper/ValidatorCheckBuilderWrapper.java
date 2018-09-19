package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.Check;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.ValidatorCheckBuilder;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class ValidatorCheckBuilderWrapper<C extends Check<R>, R, P, X> implements Supplier<ValidatorCheckBuilder<C, R, P, X>>, SaveAsWrapper<C, R, P, X> {

    private final ValidatorCheckBuilder<C, R, P, X> validatorCheckBuilder;

    public ValidatorCheckBuilderWrapper(ValidatorCheckBuilder<C, R, P, X> validatorCheckBuilder) {
        this.validatorCheckBuilder = Objects.requireNonNull(validatorCheckBuilder);
    }

    @Override
    public ValidatorCheckBuilder<C, R, P, X> get() {
        return validatorCheckBuilder;
    }

    @Override
    public CheckBuilder<C, R, P, X> toCheckBuilder() {
        return validatorCheckBuilder.exists();
    }
}
