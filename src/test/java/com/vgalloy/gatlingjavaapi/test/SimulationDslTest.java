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
package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.simulation.AssertionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultiExecutionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultiScenariosSimulation;
import com.vgalloy.gatlingjavaapi.simulation.MultipleInjectionSimulation;
import com.vgalloy.gatlingjavaapi.simulation.SimpleSimulation;
import com.vgalloy.gatlingjavaapi.simulation.WsSimulation;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
final class SimulationDslTest {

  @Test
  void simple() {
    JavaGatlingRunner.getInstance().run(SimpleSimulation.class);
  }

  @Test
  void multiScenario() {
    JavaGatlingRunner.getInstance().run(MultiScenariosSimulation.class);
  }

  @Test
  void assertion() {
    JavaGatlingRunner.getInstance().run(AssertionSimulation.class);
  }

  @Test
  void multiExecution() {
    JavaGatlingRunner.getInstance().run(MultiExecutionSimulation.class);
  }

  @Test
  void multipleInjection() {
    JavaGatlingRunner.getInstance().run(MultipleInjectionSimulation.class);
  }

  @Test
  @Disabled
  void wsSimulation() {
    JavaGatlingRunner.getInstance().run(WsSimulation.class);
  }
}
