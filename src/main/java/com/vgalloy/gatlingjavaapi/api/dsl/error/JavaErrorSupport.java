package com.vgalloy.gatlingjavaapi.api.dsl.error;

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
public final class JavaErrorSupport {

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaErrorSupport() {
        throw new AssertionError();
    }

    public static ChainBuilderWrapper tryMax(int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        return tryMax(times, UUID.randomUUID().toString(), execsWrapper);
    }

    public static ChainBuilderWrapper tryMax(int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
        Objects.requireNonNull(counterName);
        Objects.requireNonNull(execsWrapper);

        return new ChainBuilderWrapper((ChainBuilder) Predef.tryMax(Expression.of(times), counterName, execsWrapper.get()));
    }
}
