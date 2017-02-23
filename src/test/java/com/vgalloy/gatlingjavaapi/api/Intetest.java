package com.vgalloy.gatlingjavaapi.api;

import com.vgalloy.gatlingjavaapi.server.TestServerConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestServerConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Intetest {

	@LocalServerPort
	private int serverPort;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testHomePage() {
		// GIVEN
		ResponseEntity<String> c = this.restTemplate.getForEntity("/home", String.class);

		// WHEN
		Assert.assertEquals(HttpStatus.OK.value(), c.getStatusCode().value());
	}
}
