package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import java.util.Objects;
import java.util.function.Function;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expressions;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.ValidatorCheckBuilder;
import io.gatling.core.session.Session;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.check.status.HttpStatusCheckBuilder;

/**
 * Created by Vincent Galloy on 04/06/17.
 *
 * @author Vincent Galloy
 */
public final class ValidatorCheckBuilderWrapper {

    public HttpCheck is(Integer value) {
        Objects.requireNonNull(value);

        return is(Expressions.of(value));
    }

    public HttpCheck is(Function<Session, Integer> expression) {
        Objects.requireNonNull(expression);

        return is(Expressions.fromFunction(expression));
    }

    private HttpCheck is(Expression<Integer> expression) {
        ValidatorCheckBuilder validatorBuilder = HttpStatusCheckBuilder.Status().find();
        @SuppressWarnings("unchecked")
        CheckBuilder checkBuilder = validatorBuilder.is(expression);

        throw new UnsupportedOperationException();
        //
        //
        //        Expression<Integer> expression = ScalaHelper.toExpression(value);
        //
        //        CheckBuilder checkBuilder = SCALA_STATUS.is(expression);
        //
        //
        //        new HttpDsl().checkTypeStep2Check()
        //        new HttpDsl().checkBuilder2HttpCheck;
        //        HttpCheckSupport.HttpTypedConditionalCheckWrapper$.MODULE$.
        //
        //        return checkBuilder.build();
    }
}
