package com.vgalloy.gatlingjavaapi.api;

import akka.actor.ActorSystem;
import io.gatling.app.Runner;
import io.gatling.core.config.GatlingConfiguration;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class CustomRunner2 extends Runner {

	public CustomRunner2(ActorSystem system, GatlingConfiguration configuration) {
		super(system, configuration);
	}
}
