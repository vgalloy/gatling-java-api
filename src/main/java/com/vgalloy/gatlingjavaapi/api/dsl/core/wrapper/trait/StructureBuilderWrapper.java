/*
 * Copyright 2020 Vincent Galloy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
