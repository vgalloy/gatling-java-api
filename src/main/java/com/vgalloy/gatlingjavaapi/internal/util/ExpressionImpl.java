package com.vgalloy.gatlingjavaapi.internal.util;

import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import scala.runtime.AbstractFunction1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */

public final class ExpressionImpl<TYPE> extends AbstractFunction1<Session, Validation<TYPE>> implements Expression<TYPE> {

	private final TYPE object;

	ExpressionImpl(TYPE object) {
		this.object = object;
	}

	@Override
	public Validation<TYPE> apply(Session v1) {
		return new io.gatling.commons.validation.Success<>(object);
	}
}
