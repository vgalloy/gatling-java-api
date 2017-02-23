package com.vgalloy.gatlingjavaapi.api;

import akka.actor.ActorSystem;
import com.vgalloy.gatlingjavaapi.io.gatling.app.MySim;
import io.gatling.app.Runner;
import io.gatling.core.config.GatlingConfiguration;
import io.gatling.core.scenario.Simulation;
import org.junit.Test;
import scala.collection.mutable.Map$;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class GatTest extends Simulation {

	@Test
	public void simple() {

		GatlingConfiguration conf = GatlingConfiguration.load(Map$.MODULE$.empty());
		ActorSystem actorSystem = ActorSystem.create("GatlingSystem", GatlingConfiguration.loadActorSystemConfiguration());
		io.gatling.app.Runner runner = new Runner(actorSystem, conf);

//		System.getSecurityManager().ch

		scala.Option<Class<Simulation>> clazz = new scala.Some(MySim.class);
		runner.run(clazz);

	}
}