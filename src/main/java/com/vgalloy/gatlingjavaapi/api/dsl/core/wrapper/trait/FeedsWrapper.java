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

import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.SourceFeederBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.structure.StructureBuilder;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface FeedsWrapper<
        STRUCTURE extends StructureBuilder<STRUCTURE>,
        WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends ExecsWrapper<STRUCTURE, WRAPPER> {

  default WRAPPER feed(SourceFeederBuilderWrapper recordSeqFeederBuilderWrapper) {
    return feed(recordSeqFeederBuilderWrapper, 1);
  }

  @SuppressWarnings("unchecked")
  default WRAPPER feed(SourceFeederBuilderWrapper recordSeqFeederBuilderWrapper, int number) {
    Objects.requireNonNull(recordSeqFeederBuilderWrapper);

    return newInstance(
        (STRUCTURE) get().feed(recordSeqFeederBuilderWrapper.get(), Expression.of(number)));
  }
}
