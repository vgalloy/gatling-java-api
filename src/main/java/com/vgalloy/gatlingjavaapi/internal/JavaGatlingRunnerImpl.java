package com.vgalloy.gatlingjavaapi.internal;

import akka.actor.ActorSystem;
import com.vgalloy.gatlingjavaapi.api.JavaGatlingRunner;
import io.gatling.app.Runner;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.scenario.Simulation;
import scala.collection.mutable.Map$;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public enum JavaGatlingRunnerImpl implements JavaGatlingRunner {
	INSTANCE;

	@Override
	public void run(Class<? extends Simulation> simulationClass) {
		GatlingConfiguration conf = GatlingConfiguration.load(Map$.MODULE$.empty());
		ActorSystem actorSystem = ActorSystem.create("GatlingSystem", GatlingConfiguration.loadActorSystemConfiguration());
		io.gatling.app.Runner runner = new Runner(actorSystem, conf);

		scala.Option<Class<Simulation>> clazz = new scala.Some(simulationClass);
		runner.run(clazz);
	}
}
