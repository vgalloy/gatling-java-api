package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import java.util.Objects;
import java.util.function.Function;

import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.DefaultFindCheckBuilder;
import io.gatling.core.session.Session;
import io.gatling.http.Predef;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.CheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 04/06/17.
 *
 * @author Vincent Galloy
 */
public final class DefaultFindCheckBuilderWrapper {

    private final DefaultFindCheckBuilder<HttpCheck, Response, Integer> status;

    public DefaultFindCheckBuilderWrapper(DefaultFindCheckBuilder<HttpCheck, Response, Integer> status) {
        this.status = Objects.requireNonNull(status);
    }

    public CheckBuilderWrapper<HttpCheck, Response, Integer> is(Integer value) {
        Objects.requireNonNull(value);

        return is(Expression.of(value));
    }

    public CheckBuilderWrapper<HttpCheck, Response, Integer> is(Function<Session, Integer> expression) {
        Objects.requireNonNull(expression);

        return is(Expression.fromFunction(expression));
    }

    private CheckBuilderWrapper<HttpCheck, Response, Integer> is(Expression<Integer> expression) {
        return new CheckBuilderWrapper<HttpCheck, Response, Integer>(status.find().is(expression)) {
            @Override
            public CheckMaterializer getCheckMaterializer() {
                return Predef.httpStatusCheckMaterializer();
            }
        };
    }
}
