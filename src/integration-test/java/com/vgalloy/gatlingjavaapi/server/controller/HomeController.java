/*
 * Copyright 2020 Vincent Galloy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vgalloy.gatlingjavaapi.server.controller;

import com.vgalloy.gatlingjavaapi.server.model.SimpleModel;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
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

  @GetMapping("hasHeader")
  public String hasHeader(final HttpServletRequest httpServletRequest) {
    final String headerValue = httpServletRequest.getHeader("testHeader");
    LOGGER.info("headerValue : " + headerValue);
    if (Objects.isNull(headerValue)) {
      throw new IllegalStateException("No header value");
    }
    return "hasHeader";
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
