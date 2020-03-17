package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.StructureBuilder;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface ErrorWrapper<
        STRUCTURE extends StructureBuilder<STRUCTURE>,
        WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends ExecsWrapper<STRUCTURE, WRAPPER> {

  default WRAPPER tryMax(int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    return tryMax(times, UUID.randomUUID().toString(), execsWrapper);
  }

  default WRAPPER tryMax(
      int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    Objects.requireNonNull(counterName);
    Objects.requireNonNull(execsWrapper);

    return newInstance(get().tryMax(Expression.of(times), counterName, execsWrapper.get()));
  }

  default WRAPPER exitHereIfFailed() {
    return newInstance(get().exitHereIfFailed());
  }
}
