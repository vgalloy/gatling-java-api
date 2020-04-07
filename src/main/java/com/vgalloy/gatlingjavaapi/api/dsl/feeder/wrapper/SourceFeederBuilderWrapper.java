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
package com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper;

import io.gatling.core.feeder.FeederBuilderBase;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class SourceFeederBuilderWrapper<T extends FeederBuilderBase<String>>
    implements Supplier<T> {

  private final T instance;

  public SourceFeederBuilderWrapper(final T instance) {
    this.instance = Objects.requireNonNull(instance, "instance");
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> queue() {
    return new SourceFeederBuilderWrapper<>(instance.queue());
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> random() {
    return new SourceFeederBuilderWrapper<>(instance.random());
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> shuffle() {
    return new SourceFeederBuilderWrapper<>(instance.shuffle());
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> circular() {
    return new SourceFeederBuilderWrapper<>(instance.circular());
  }

  @Override
  public T get() {
    return instance;
  }
}
