package com.vgalloy.gatlingjavaapi.internal.impl;

import java.util.Objects;

import akka.actor.ActorSystem;
import io.gatling.app.RunResult;
import io.gatling.app.Runner;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.scenario.Simulation;
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
        GatlingConfiguration conf = GatlingConfiguration.load(Map$.MODULE$.empty());

        String actorSystemName = "GatlingSystem" /* + uuid.toString()*/;

        ActorSystem actorSystem = ActorSystem.create(actorSystemName, GatlingConfiguration.loadActorSystemConfiguration());
        io.gatling.app.Runner runner = new Runner(actorSystem, conf);

        @SuppressWarnings("unchecked")
        scala.Option<Class<Simulation>> clazz = new scala.Some<>((Class<Simulation>) simulationClass);
        try {
            return runner.run(clazz);
        } finally {
            actorSystem.terminate();
        }
    }
}
