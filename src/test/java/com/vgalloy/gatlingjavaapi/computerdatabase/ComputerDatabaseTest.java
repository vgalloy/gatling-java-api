package com.vgalloy.gatlingjavaapi.computerdatabase;

import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ComputerDatabaseTest {

  @Test
  public void advancedSimulationStep05() {
    JavaGatlingRunner.getInstance().run(AdvancedSimulationStep05.class);
  }
}
