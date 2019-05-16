package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import scala.Some;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface SaveAsWrapper<T, P, X> {

  CheckBuilder<T, P, X> toCheckBuilder();

  CheckMaterializer checkMaterializer();

  default CheckBuilderWrapper<T, P, X> saveAs(String key) {
    final CheckBuilder<T, P, X> checkBuilder = toCheckBuilder();
    final CheckBuilder<T, P, X> newInstance =
        checkBuilder.copy(
            checkBuilder.extractor(),
            checkBuilder.validator(),
            checkBuilder.displayActualValue(),
            checkBuilder.customName(),
            new Some(key));
    return new CheckBuilderWrapper<T, P, X>(newInstance) {

      @Override
      public CheckMaterializer getCheckMaterializer() {
        return checkMaterializer();
      }
    };
  }
}
