package com.vgalloy.gatlingjavaapi.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
@RestController
public class HomeController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() throws Exception {
        Thread.sleep(100);
        return "home";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post() throws Exception {
        return "post";
    }
}
