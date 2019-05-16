package com.vgalloy.gatlingjavaapi.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class TestServerConfig {

  public static void main(String[] args) {
    new SpringApplicationBuilder(TestServerConfig.class).run(args);
  }
}
