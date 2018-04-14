package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import java.util.Objects;
import java.util.function.Function;

import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.DefaultFindCheckBuilder;
import io.gatling.core.session.Session;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 04/06/17.
 *
 * @author Vincent Galloy
 */
public final class DefaultFindCheckBuilderWrapper {

    private final DefaultFindCheckBuilder<HttpCheck, Response, Response, Integer> status;

    public DefaultFindCheckBuilderWrapper(DefaultFindCheckBuilder<HttpCheck, Response, Response, Integer> status) {
        this.status = Objects.requireNonNull(status);
    }

    public CheckBuilder<HttpCheck, Response, Response, Integer> is(Integer value) {
        Objects.requireNonNull(value);

        return is(Expression.of(value));
    }

    public CheckBuilder<HttpCheck, Response, Response, Integer> is(Function<Session, Integer> expression) {
        Objects.requireNonNull(expression);

        return is(Expression.fromFunction(expression));
    }

    private CheckBuilder<HttpCheck, Response, Response, Integer> is(Expression<Integer> expression) {
        return status.find().is(expression);
    }
}
