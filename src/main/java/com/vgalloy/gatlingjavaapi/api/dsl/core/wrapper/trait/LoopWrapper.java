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
        STRUCTURE extends StructureBuilder<STRUCTURE>,
        WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends ExecsWrapper<STRUCTURE, WRAPPER> {

  default WRAPPER repeat(
      final int times, final ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    return repeat(times, UUID.randomUUID().toString(), execsWrapper);
  }

  @SuppressWarnings("unchecked")
  default WRAPPER repeat(
      final int times,
      final String counterName,
      final ExecsWrapper<? extends ChainBuilder, ?> execsWrapper) {
    Objects.requireNonNull(counterName, "counterName");
    Objects.requireNonNull(execsWrapper, "execsWrapper");

    return newInstance(
        (STRUCTURE) get().repeat(Expression.of(times), counterName, execsWrapper.get()));
  }
}
