package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.session.Session;
import io.gatling.core.structure.Execs;
import io.gatling.core.structure.StructureBuilder;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface StructureBuilderWrapper<
        STRUCTURE extends StructureBuilder<STRUCTURE>,
        WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends ExecsWrapper<STRUCTURE, WRAPPER>,
        PausesWrapper<STRUCTURE, WRAPPER>,
        LoopWrapper<STRUCTURE, WRAPPER>,
        FeedsWrapper<STRUCTURE, WRAPPER>,
        ErrorWrapper<STRUCTURE, WRAPPER> {

  default WRAPPER exec(final ActionBuilderSupplier actionBuilderSupplier) {
    Objects.requireNonNull(actionBuilderSupplier);

    return newInstance(get().exec(actionBuilderSupplier.toActionBuilder()));
  }

  default WRAPPER exec(final ChainBuilderWrapper... structureSupportWrappers) {
    Objects.requireNonNull(structureSupportWrappers);

    final scala.collection.immutable.List<Execs<?>> list =
        Arrays.stream(structureSupportWrappers)
            .map(ChainBuilderWrapper::get)
            .collect(ScalaHelper.toScalaList());
    return newInstance(get().exec(list.toSeq()));
  }

  default WRAPPER exec(final Function<Session, Session> sessionFunction) {
    return newInstance(get().exec(Expression.fromFunction(sessionFunction)));
  }
}
