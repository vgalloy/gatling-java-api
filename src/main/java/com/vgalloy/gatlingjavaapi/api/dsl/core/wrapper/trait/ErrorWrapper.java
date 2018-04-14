package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import java.util.Objects;
import java.util.UUID;

import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.StructureBuilder;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface ErrorWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends ExecsWrapper<STRUCTURE, WRAPPER> {

    @SuppressWarnings("unchecked")
    default WRAPPER tryMax(int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        return tryMax(times, UUID.randomUUID().toString(), execsWrapper);
    }

    @SuppressWarnings("unchecked")
    default WRAPPER tryMax(int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        Objects.requireNonNull(counterName);
        Objects.requireNonNull(execsWrapper);

        return newInstance((STRUCTURE) get().tryMax(Expression.of(times), counterName, execsWrapper.get()));
    }

    @SuppressWarnings("unchecked")
    default WRAPPER exitHereIfFailed() {
        return newInstance((STRUCTURE) get().exitHereIfFailed());
    }
}
