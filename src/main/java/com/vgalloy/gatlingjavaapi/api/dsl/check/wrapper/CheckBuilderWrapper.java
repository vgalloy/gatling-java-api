package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 06/12/18.
 *
 * @author Vincent Galloy
 */
public abstract class CheckBuilderWrapper<T, X, P> implements Supplier<CheckBuilder<T, X, P>> {

  private final CheckBuilder<T, X, P> checkBuilder;

  public CheckBuilderWrapper(final CheckBuilder<T, X, P> checkBuilder) {
    this.checkBuilder = Objects.requireNonNull(checkBuilder);
  }

  @Override
  public CheckBuilder<T, X, P> get() {
    return checkBuilder;
  }

  public abstract CheckMaterializer getCheckMaterializer();
}
