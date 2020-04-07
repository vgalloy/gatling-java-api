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
package com.vgalloy.gatlingjavaapi.internal.util.expression;

import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import java.util.function.Function;
import scala.Function1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface Expression<TYPE> extends Function1<Session, Validation<TYPE>> {

  static <T> Expression<T> fromFunction(Function<Session, T> function) {
    return new FunctionExpressionImpl<>(function);
  }

  static <T> Expression<T> of(T item) {
    return new SuccessExpressionImpl<>(item);
  }
}
