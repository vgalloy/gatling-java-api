package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 06/12/18.
 *
 * @author Vincent Galloy
 */
public class CheckBuilderWrapper<T, P, X> implements Supplier<CheckBuilder<T, P, X>> {

  private final CheckBuilder<T, P, X> instance;
  private final CheckMaterializer<T, HttpCheck, Response, P> materializer;

  public CheckBuilderWrapper(
      final CheckBuilder<T, P, X> instance,
      final CheckMaterializer<T, HttpCheck, Response, P> materializer) {
    this.instance = Objects.requireNonNull(instance, "instance");
    this.materializer = Objects.requireNonNull(materializer, "materializer");
  }

  @Override
  public CheckBuilder<T, P, X> get() {
    return instance;
  }

  public CheckMaterializer<T, HttpCheck, Response, P> getMaterializer() {
    return materializer;
  }
}
