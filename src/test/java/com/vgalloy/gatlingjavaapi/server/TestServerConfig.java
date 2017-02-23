package com.vgalloy.gatlingjavaapi.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Vincent Galloy.
 *         Created by Vincent Galloy on 23/02/2017.
 */
@SpringBootApplication
public class TestServerConfig {

	public static void main(String[] args) {
		new SpringApplicationBuilder(TestServerConfig.class)
				.run(args);
	}

}
