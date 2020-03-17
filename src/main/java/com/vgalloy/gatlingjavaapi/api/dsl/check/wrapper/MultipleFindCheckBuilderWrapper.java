package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.MultipleFindCheckBuilder;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public class MultipleFindCheckBuilderWrapper<T, P, X>
    implements FindCheckBuilderWrapper<T, P, X, MultipleFindCheckBuilder<T, P, X>> {

  private final MultipleFindCheckBuilder<T, P, X> instance;
  private final CheckMaterializer<T, HttpCheck, Response, P> materializer;

  public MultipleFindCheckBuilderWrapper(
      final MultipleFindCheckBuilder<T, P, X> instance,
      final CheckMaterializer<T, HttpCheck, Response, P> materializer) {
    this.instance = Objects.requireNonNull(instance, "instance");
    this.materializer = Objects.requireNonNull(materializer, "materializer");
    ;
  }

  @Override
  public MultipleFindCheckBuilder<T, P, X> get() {
    return instance;
  }

  @Override
  public CheckMaterializer<T, HttpCheck, Response, P> getMaterializer() {
    return materializer;
  }
}
