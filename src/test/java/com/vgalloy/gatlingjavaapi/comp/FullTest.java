package com.vgalloy.gatlingjavaapi.comp;

import com.vgalloy.gatlingjavaapi.api.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.server.TestServerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestServerConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FullTest {

	@LocalServerPort
	private int serverPort;

	@Test
	public void full() {
		Simu.port = serverPort;

		JavaGatlingRunner.getRunner().run(Simu.class);
	}
}
