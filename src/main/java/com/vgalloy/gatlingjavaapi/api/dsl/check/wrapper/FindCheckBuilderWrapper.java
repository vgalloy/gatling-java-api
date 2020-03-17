package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.FindCheckBuilder;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface FindCheckBuilderWrapper<T, P, X, STRUCTURE extends FindCheckBuilder<T, P, X>>
    extends Supplier<STRUCTURE>, SaveAsWrapper<T, P, X> {

  default ValidatorCheckBuilderWrapper<T, P, X> toValidatorCheckBuilder() {
    return new ValidatorCheckBuilderWrapper<>(get().find(), getMaterializer());
  }

  @Override
  default CheckBuilderWrapper<T, P, X> toCheckBuilder() {
    return new CheckBuilderWrapper<>(get().find().exists(), getMaterializer());
  }
}
