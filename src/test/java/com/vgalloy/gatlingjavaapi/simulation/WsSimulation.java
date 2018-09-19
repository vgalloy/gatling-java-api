package com.vgalloy.gatlingjavaapi.simulation;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;

import java.util.concurrent.TimeUnit;

import static com.vgalloy.gatlingjavaapi.api.dsl.check.JavaCheckSupport.wsAwait;
import static com.vgalloy.gatlingjavaapi.api.dsl.check.JavaCheckSupport.wsListen;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDSL.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDSL.http;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDSL.ws;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsSimulation extends SimulationWrapper {

    @Override
    protected void configure() {
        HttpProtocolBuilderWrapper httpConf = http()
            .baseURL("http://localhost:" + 9999)
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

        ScenarioBuilderWrapper scn = scenario("WebSocket")
            .exec(http("Home").get("/"))
            .pause(1, TimeUnit.MICROSECONDS)
            .exec(http("Login").get("/room?username=${id}"))
            .pause(1, TimeUnit.MICROSECONDS)
            .exec(ws("Connect WS").open("/room/chat?username=${id}"))
            .pause(1, TimeUnit.MICROSECONDS)
            .repeat(2, "i",
                exec(ws("Say Hello WS")
                    .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}"))
                    .pause(1, TimeUnit.MICROSECONDS)
            ).exec(ws("Message1")
                .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
                .check(wsAwait().within(30, TimeUnit.MICROSECONDS).expect(1).jsonPath("$.message").findAll().saveAs("message1"))
            ).exec(ws("Message2")
                .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
                .check(wsListen().within(30, TimeUnit.MICROSECONDS).until(1).jsonPath("$.message").saveAs("message2"))
            ).exec(ws("Message3")
                .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
                .check(wsAwait().within(30, TimeUnit.MICROSECONDS).expect(1).regex("$.message").saveAs("message3"))
            ).exec(ws("Message3")
                .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
                .check(wsListen().within(30, TimeUnit.MICROSECONDS).expect(1).message())
            ).exec(ws("Message3")
                .check(wsListen().within(30, TimeUnit.MICROSECONDS).expect(1).message())
            ).exec(ws("Cancel")
                .wsName("foo")
                .cancelCheck()
            ).exec(ws("Message4")
                .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
                .check(wsAwait().within(30, TimeUnit.MICROSECONDS).until(1))
            ).exec(ws("Close WS").close())
            .exec(ws("Open Named", "foo").open("/bar"));

        setUp(
            scn.inject(atOnceUsers(1))
        ).protocols(httpConf);
    }
}
