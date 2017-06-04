package com.vgalloy.gatlingjavaapi.internal.util.expression;

import java.util.function.Function;

import io.gatling.core.session.Session;

/**
 * Created by Vincent Galloy on 04/06/17.
 *
 * @author Vincent Galloy
 */
public final class Expressions {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private Expressions() {
        throw new AssertionError();
    }

    public static <T> Expression<T> fromFunction(Function<Session, T> function) {
        return new FunctionExpressionImpl<>(function);
    }

    public static <T> Expression<T> of(T item) {
        return new SuccessExpressionImpl<>(item);
    }
}
