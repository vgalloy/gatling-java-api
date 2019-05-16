package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.Predef;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.ValidatorCheckBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class ValidatorCheckBuilderWrapper<T, P, X>
    implements Supplier<ValidatorCheckBuilder<T, P, X>>, SaveAsWrapper<T, P, X> {

  private final ValidatorCheckBuilder<T, P, X> validatorCheckBuilder;

  public ValidatorCheckBuilderWrapper(ValidatorCheckBuilder<T, P, X> validatorCheckBuilder) {
    this.validatorCheckBuilder = Objects.requireNonNull(validatorCheckBuilder);
  }

  @Override
  public ValidatorCheckBuilder<T, P, X> get() {
    return validatorCheckBuilder;
  }

  @Override
  public CheckBuilder<T, P, X> toCheckBuilder() {
    return Predef.validatorCheckBuilder2CheckBuilder(validatorCheckBuilder);
  }

  @Override
  public CheckMaterializer checkMaterializer() {
    throw new UnsupportedOperationException();
  }
}
