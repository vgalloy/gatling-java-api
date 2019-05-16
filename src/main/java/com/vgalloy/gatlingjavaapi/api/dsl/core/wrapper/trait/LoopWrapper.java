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
public interface LoopWrapper<
        STRUCTURE extends StructureBuilder,
        WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends ExecsWrapper<STRUCTURE, WRAPPER> {

  @SuppressWarnings("unchecked")
  default WRAPPER repeat(int times, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    return repeat(times, UUID.randomUUID().toString(), execsWrapper);
  }

  @SuppressWarnings("unchecked")
  default WRAPPER repeat(
      int times, String counterName, ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    Objects.requireNonNull(counterName);
    Objects.requireNonNull(execsWrapper);

    return newInstance(
        (STRUCTURE) get().repeat(Expression.of(times), counterName, execsWrapper.get()));
  }
}
