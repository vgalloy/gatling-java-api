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

import io.gatling.core.structure.StructureBuilder;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @param <STRUCTURE> The gatling structure wrapped in the wrapper
 * @param <WRAPPER> the wrapper it self
 * @author Vincent Galloy.
 */
public interface ExecsWrapper<
        STRUCTURE extends StructureBuilder<STRUCTURE>,
        WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends Supplier<STRUCTURE> {

  /**
   * Build a new instance of the wrapper
   *
   * @param newStructure the wrapped gatling structure
   * @return the new wrapper
   */
  WRAPPER newInstance(STRUCTURE newStructure);
}
