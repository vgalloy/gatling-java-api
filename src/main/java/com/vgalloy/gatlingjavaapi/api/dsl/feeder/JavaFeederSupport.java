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
package com.vgalloy.gatlingjavaapi.api.dsl.feeder;

import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.SourceFeederBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import io.gatling.core.Predef;
import io.gatling.core.feeder.BatchableFeederBuilder;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaFeederSupport {

  /** Constructor. To prevent external instantiation */
  private JavaFeederSupport() {
    throw new AssertionError("No instance of JavaFeederSupport");
  }

  public static SourceFeederBuilderWrapper<BatchableFeederBuilder<String>> csv(
      final String fileName) {
    Objects.requireNonNull(fileName, "fileName");

    final BatchableFeederBuilder<String> recordSeqFeederBuilder =
        Predef.csv(fileName, '\"', GatlingConfigurationSupplier.GATLING_CONFIGURATION);
    return new SourceFeederBuilderWrapper<>(recordSeqFeederBuilder);
  }
}
