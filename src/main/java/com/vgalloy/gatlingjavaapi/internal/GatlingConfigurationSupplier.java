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
package com.vgalloy.gatlingjavaapi.internal;

import io.gatling.core.config.GatlingConfiguration;
import scala.collection.mutable.Map$;

/**
 * Created by Vincent Galloy on 27/02/2017.
 *
 * @author Vincent Galloy.
 */
public class GatlingConfigurationSupplier {

  public static final GatlingConfiguration GATLING_CONFIGURATION =
      GatlingConfiguration.load(Map$.MODULE$.empty());
}
