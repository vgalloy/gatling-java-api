package com.vgalloy.gatlingjavaapi.internal.util.expression;

import java.util.Objects;
import java.util.function.Function;

import io.gatling.commons.validation.Success;
import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import scala.runtime.AbstractFunction1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
final class FunctionExpressionImpl<TYPE> extends AbstractFunction1<Session, Validation<TYPE>> implements Expression<TYPE> {

    private final Function<Session, TYPE> function;

    FunctionExpressionImpl(Function<Session, TYPE> function) {
        this.function = Objects.requireNonNull(function);
    }

    @Override
    public Validation<TYPE> apply(Session session) {
        return new Success<>(function.apply(session));
    }
}
