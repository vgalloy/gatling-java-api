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

import io.gatling.commons.validation.Success;
import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import java.util.Objects;
import java.util.function.Function;
import scala.runtime.AbstractFunction1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
final class FunctionExpressionImpl<TYPE> extends AbstractFunction1<Session, Validation<TYPE>>
    implements Expression<TYPE> {

  private final Function<Session, TYPE> function;

  FunctionExpressionImpl(Function<Session, TYPE> function) {
    this.function = Objects.requireNonNull(function);
  }

  @Override
  public Validation<TYPE> apply(Session session) {
    return new Success<>(function.apply(session));
  }
}
