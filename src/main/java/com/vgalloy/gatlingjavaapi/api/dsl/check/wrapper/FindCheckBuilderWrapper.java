package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.Check;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.FindCheckBuilder;
import io.gatling.core.check.ValidatorCheckBuilder;

import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface FindCheckBuilderWrapper<C extends Check<R>, R, P, X, STRUCTURE extends FindCheckBuilder<C, R, P, X>>
        extends Supplier<STRUCTURE>, SaveAsWrapper<C, R, P, X> {

    default ValidatorCheckBuilderWrapper<C, R, P, X> find() {
        return new ValidatorCheckBuilderWrapper<>(get().find());
    }

    default CheckBuilder<C, R, P, X> toCheckBuilder() {
        return get().find().exists();
    }

    default C findCheckBuilder2Check() {
        return toCheckBuilder().build();
    }
}
