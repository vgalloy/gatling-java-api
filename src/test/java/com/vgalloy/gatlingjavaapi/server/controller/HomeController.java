package com.vgalloy.gatlingjavaapi.server.controller;

import com.vgalloy.gatlingjavaapi.server.model.SimpleModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
@RestController
class HomeController {

    @GetMapping("home")
    public String home() throws Exception {
        Thread.sleep(100);
        return "home";
    }

    @PostMapping("post")
    public String post(@RequestBody SimpleModel simpleModel) throws Exception {
        return "post";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @GetMapping("error")
    public String error() throws Exception {
        return "post";
    }
}
