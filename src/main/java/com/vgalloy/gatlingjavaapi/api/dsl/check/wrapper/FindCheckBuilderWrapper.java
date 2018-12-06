package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import java.util.function.Supplier;

import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.FindCheckBuilder;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface FindCheckBuilderWrapper<T, P, X, STRUCTURE extends FindCheckBuilder<T, P, X>>
        extends Supplier<STRUCTURE>, SaveAsWrapper<T, P, X> {

    default ValidatorCheckBuilderWrapper<T, P, X> find() {
        return new ValidatorCheckBuilderWrapper<>(get().find());
    }

    default CheckBuilder<T, P, X> toCheckBuilder() {
        return get().find().exists();
    }
//
//    default C findCheckBuilder2Check() {
//        return toCheckBuilder().build();
//    }
}
