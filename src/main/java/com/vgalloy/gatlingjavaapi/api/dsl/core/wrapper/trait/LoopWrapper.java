package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import java.util.Objects;
import java.util.UUID;

import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.StructureBuilder;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expressions;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface LoopWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends ExecsWrapper<STRUCTURE, WRAPPER> {

    @SuppressWarnings("unchecked")
    default WRAPPER repeat(int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        Objects.requireNonNull(execsWrapper);

        return newInstance((STRUCTURE) get().repeat(Expressions.of(times), UUID.randomUUID().toString(), execsWrapper.get()));
    }

    @SuppressWarnings("unchecked")
    default WRAPPER repeat(int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        Objects.requireNonNull(counterName);
        Objects.requireNonNull(execsWrapper);

        return newInstance((STRUCTURE) get().repeat(Expressions.of(times), counterName, execsWrapper.get()));
    }
}
