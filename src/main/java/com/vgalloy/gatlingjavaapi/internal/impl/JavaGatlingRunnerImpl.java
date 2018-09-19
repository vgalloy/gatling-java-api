package com.vgalloy.gatlingjavaapi.internal.impl;

import java.util.Objects;

import akka.actor.ActorSystem;
import io.gatling.app.RunResult;
import io.gatling.app.Runner;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.scenario.Simulation;
import scala.Option;
import scala.Some;
import scala.collection.mutable.Map$;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;

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
        final ActorSystem actorSystem = ActorSystem.create(actorSystemName, GatlingConfiguration.loadActorSystemConfiguration());
        final Runner runner = new Runner(actorSystem, conf);

        @SuppressWarnings("unchecked")
        final Option<Class<Simulation>> clazz = new Some<>((Class<Simulation>) simulationClass);
        try {
            return runner.run(clazz);
        } finally {
            actorSystem.terminate();
        }
    }
}
