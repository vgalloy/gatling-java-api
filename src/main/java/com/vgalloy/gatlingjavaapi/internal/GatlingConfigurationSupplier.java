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
