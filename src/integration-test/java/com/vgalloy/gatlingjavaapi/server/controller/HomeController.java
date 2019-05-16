package com.vgalloy.gatlingjavaapi.server.controller;

import com.vgalloy.gatlingjavaapi.server.model.SimpleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
@RestController
public class HomeController {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

  @GetMapping("home")
  public String home() throws Exception {
    LOGGER.info("home");
    Thread.sleep(100);
    return "home";
  }

  @GetMapping("get/{id}")
  public String get(@PathVariable String id) {
    LOGGER.info("get : " + id);
    return "get";
  }

  @PostMapping("post")
  public String post(@RequestBody SimpleModel simpleModel) {
    LOGGER.info("post : " + simpleModel);
    return "post";
  }

  @PutMapping("put")
  public String put() {
    LOGGER.info("put");
    return "put";
  }

  @DeleteMapping("delete")
  public String delete() {
    LOGGER.info("delete");
    return "delete";
  }

  @GetMapping("error")
  public String error() {
    throw new RuntimeException("error");
  }
}
