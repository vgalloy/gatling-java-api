package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckBuilder;
import java.util.Objects;
import scala.Some;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface SaveAsWrapper<T, P, X> extends ToCheckBuilder<T, P, X> {

  default CheckBuilderWrapper<T, P, X> saveAs(final String key) {
    Objects.requireNonNull(key, "key");
    final CheckBuilder<T, P, X> checkBuilder = toCheckBuilder().get();
    final CheckBuilder<T, P, X> newInstance =
        checkBuilder.copy(
            checkBuilder.extractor(),
            checkBuilder.validator(),
            checkBuilder.displayActualValue(),
            checkBuilder.customName(),
            new Some<>(key));
    return new CheckBuilderWrapper<>(newInstance, getMaterializer());
  }
}
