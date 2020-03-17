package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.Predef;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.ValidatorCheckBuilder;
import io.gatling.core.session.Session;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class ValidatorCheckBuilderWrapper<T, P, X>
    implements Supplier<ValidatorCheckBuilder<T, P, X>>, SaveAsWrapper<T, P, X> {

  private final ValidatorCheckBuilder<T, P, X> instance;
  private final CheckMaterializer<T, HttpCheck, Response, P> materializer;

  public ValidatorCheckBuilderWrapper(
      final ValidatorCheckBuilder<T, P, X> instance,
      final CheckMaterializer<T, HttpCheck, Response, P> materializer) {
    this.instance = Objects.requireNonNull(instance, "instance");
    this.materializer = Objects.requireNonNull(materializer, "materializer");
  }

  @Override
  public ValidatorCheckBuilder<T, P, X> get() {
    return instance;
  }

  public CheckBuilderWrapper<T, P, X> is(final X value) {
    Objects.requireNonNull(value, "value");

    return is(Expression.of(value));
  }

  public CheckBuilderWrapper<T, P, X> is(final Function<Session, X> expression) {
    Objects.requireNonNull(expression, "expression");

    return is(Expression.fromFunction(expression));
  }

  private CheckBuilderWrapper<T, P, X> is(final Expression<X> expression) {
    final CheckBuilder<T, P, X> checkBuilder = instance.is(expression, Objects::equals);
    return new CheckBuilderWrapper<>(checkBuilder, materializer);
  }

  @Override
  public CheckBuilderWrapper<T, P, X> toCheckBuilder() {
    return new CheckBuilderWrapper<>(
        Predef.validatorCheckBuilder2CheckBuilder(instance), materializer);
  }

  @Override
  public CheckMaterializer<T, HttpCheck, Response, P> getMaterializer() {
    return materializer;
  }
}
