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
package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.FindCheckBuilder;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface FindCheckBuilderWrapper<T, P, X, STRUCTURE extends FindCheckBuilder<T, P, X>>
    extends Supplier<STRUCTURE>, SaveAsWrapper<T, P, X> {

  default ValidatorCheckBuilderWrapper<T, P, X> toValidatorCheckBuilder() {
    return new ValidatorCheckBuilderWrapper<>(get().find(), getMaterializer());
  }

  @Override
  default CheckBuilderWrapper<T, P, X> toCheckBuilder() {
    return new CheckBuilderWrapper<>(get().find().exists(), getMaterializer());
  }
}
