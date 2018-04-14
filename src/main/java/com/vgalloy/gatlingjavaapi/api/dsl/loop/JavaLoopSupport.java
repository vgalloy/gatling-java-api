package com.vgalloy.gatlingjavaapi.api.dsl.loop;

import java.util.Objects;
import java.util.UUID;

import io.gatling.core.Predef;
import io.gatling.core.structure.ChainBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait.ExecsWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaLoopSupport {

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaLoopSupport() {
        throw new AssertionError();
    }

    public static ChainBuilderWrapper repeat(int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        return repeat(times, UUID.randomUUID().toString(), execsWrapper);
    }

    public static ChainBuilderWrapper repeat(int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        Objects.requireNonNull(counterName);
        Objects.requireNonNull(execsWrapper);

        return new ChainBuilderWrapper((ChainBuilder) Predef.repeat(Expression.of(times), counterName, execsWrapper.get()));
    }
}
