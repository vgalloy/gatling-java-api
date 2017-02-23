package com.vgalloy.gatlingjavaapi.api;

import akka.actor.ActorSystem;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.scenario.Simulation;
import io.gatling.core.scenario.SimulationParams;
import scala.collection.mutable.Map$;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class CustomRunner {

	GatlingConfiguration conf = GatlingConfiguration.load(Map$.MODULE$.empty());
	ActorSystem actorSystem = ActorSystem.create("GatlingSystem", GatlingConfiguration.loadActorSystemConfiguration());
	io.gatling.app.Runner runner = new io.gatling.app.Runner(actorSystem, conf);

	public <T extends Simulation> void run(T simulation) {

//		Selection selection = new Selection(simulation.getClass(), conf);

		Objects.requireNonNull(simulation);
		System.out.println("Simulation instantiated");

		SimulationParams simulationParams = simulation.params(conf);
		System.out.println("Simulation params built");

		simulation.executeBefore();
		System.out.println("Before hooks executed");

//		RunMessage runMessage = new RunMessage("simName", selection)
//
//
//		CoreComponents coreComponents = new CoreComponents()

		runner.start(simulationParams, null, null);
	}
}
