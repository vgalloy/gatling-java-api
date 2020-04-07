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
package com.vgalloy.gatlingjavaapi.internal.impl;

import akka.actor.ActorSystem;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import io.gatling.app.RunResult;
import io.gatling.app.Runner;
import io.gatling.commons.util.DefaultClock;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.scenario.Simulation;
import java.util.Objects;
import scala.Option;
import scala.Some;
import scala.collection.mutable.Map$;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public enum JavaGatlingRunnerImpl implements JavaGatlingRunner {
  INSTANCE;

  @Override
  public synchronized RunResult run(JavaSimulation javaSimulation) {
    Objects.requireNonNull(javaSimulation);
    InternalSimulationWrapper.javaSimulation = javaSimulation;
    return run(InternalSimulationWrapper.class);
  }

  @Override
  public synchronized RunResult run(Class<? extends Simulation> simulationClass) {
    final GatlingConfiguration conf = GatlingConfiguration.load(Map$.MODULE$.empty());
    final String actorSystemName = "GatlingSystem" /* + uuid.toString()*/;
    final ActorSystem actorSystem =
        ActorSystem.create(actorSystemName, GatlingConfiguration.loadActorSystemConfiguration());
    final io.gatling.app.Runner runner = new Runner(actorSystem, new DefaultClock(), conf);

    @SuppressWarnings("unchecked")
    final Option<Class<Simulation>> clazz = new Some<>((Class<Simulation>) simulationClass);
    try {
      return runner.run(clazz);
    } finally {
      actorSystem.terminate();
    }
  }
}
