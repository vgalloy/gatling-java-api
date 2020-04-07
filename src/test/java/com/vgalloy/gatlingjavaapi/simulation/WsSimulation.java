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
package com.vgalloy.gatlingjavaapi.simulation;

import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.checkTextMessage;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.ws;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsSimulation extends SimulationWrapper {

  @Override
  protected void configure() {
    HttpProtocolBuilderWrapper httpConf =
        http()
            .baseURL("ws://localhost:9000")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0");

    ScenarioBuilderWrapper scn =
        scenario("WebSocket")
            .exec(http("Home").get("/"))
            .pause(1)
            .exec(session -> session.set("id", "Steph" + session.userId()))
            .exec(http("Login").get("/room?username=${id}"))
            .pause(1)
            .exec(
                ws("Connect WS")
                    .connect("/room/chat?username=${id}")
                    .subprotocol("FOO")
                    //              .await(1, TimeUnit.MICROSECONDS,
                    //                  checkTextMessage("checkName")
                    //                    .matching(jsonPath("$.uuid").is("${correlation}"))
                    //                    .check(jsonPath("$.code").ofType[Int].is(1))
                    //              )
                    .await(1, TimeUnit.MILLISECONDS, checkTextMessage("checkName"))
                    .onConnected(exec(ws("Perform auth").sendText("Some auth token")).pause(1)))
            .pause(1)
            .repeat(
                2,
                "i",
                exec(ws("Say Hello WS")
                        .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}"))
                    .pause(1, TimeUnit.MICROSECONDS))
            //          .exec(ws("Message1").wsName("foo")
            //              .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
            //              .await(30, TimeUnit.MICROSECONDS,
            //
            // checkTextMessage("checkName1").check(jsonPath("$.message").findAll().saveAs("message1"))
            //              )
            //              .await(30, TimeUnit.MICROSECONDS,
            //
            // checkTextMessage("checkName2").check(jsonPath("$.message").findAll().saveAs("message2"))
            //              )
            //          )
            //          .exec(ws("Message2")
            //              .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
            //              .await(30, TimeUnit.MICROSECONDS,
            //                checkTextMessage("checkName1").check(
            //                  regex("somePattern1").saveAs("message1"),
            //                  regex("somePattern2").saveAs("message2")),
            //
            // checkTextMessage("checkName2").check(regex("somePattern2").saveAs("message2")))
            //          )
            //          .exec(ws("Message3")
            //              .sendText("{\"text\": \"Hello, I'm ${id} and this is message ${i}!\"}")
            //              .await(30, TimeUnit.MICROSECONDS,
            //                checkTextMessage("checkName"))
            //          )
            //          .exec(ws("Message3")
            //              .sendBytes("hello".getBytes())
            //              .await(30, TimeUnit.MICROSECONDS,
            //
            // checkBinaryMessage("checkName").check(bodyBytes.transform(_.length).saveAs("bytesLength")))
            //          )
            .exec(ws("Close WS").close())
            .exec(ws("Open Named", "foo").connect("/bar"));

    setUp(scn.inject(atOnceUsers(1))).protocols(httpConf);
  }
}
