package com.vgalloy.gatlingjavaapi.internal.util.expression;

import io.gatling.commons.validation.Success;
import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import scala.runtime.AbstractFunction1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */

final class SuccessExpressionImpl<TYPE> extends AbstractFunction1<Session, Validation<TYPE>> implements Expression<TYPE> {

    private final Success<TYPE> object;

    SuccessExpressionImpl(TYPE object) {
        this.object = new Success<>(object);
    }

    @Override
    public Validation<TYPE> apply(Session session) {
        return object;
    }
}
