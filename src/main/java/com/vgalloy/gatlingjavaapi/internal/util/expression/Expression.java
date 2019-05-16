package com.vgalloy.gatlingjavaapi.internal.util.expression;

import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import java.util.function.Function;
import scala.Function1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface Expression<TYPE> extends Function1<Session, Validation<TYPE>> {

  static <T> Expression<T> fromFunction(Function<Session, T> function) {
    return new FunctionExpressionImpl<>(function);
  }

  static <T> Expression<T> of(T item) {
    return new SuccessExpressionImpl<>(item);
  }
}
