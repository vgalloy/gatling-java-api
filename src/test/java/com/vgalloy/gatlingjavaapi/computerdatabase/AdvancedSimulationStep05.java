package com.vgalloy.gatlingjavaapi.computerdatabase;

import static com.vgalloy.gatlingjavaapi.api.dsl.check.JavaCheckSupport.css;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.rampUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.error.JavaErrorSupport.tryMax;
import static com.vgalloy.gatlingjavaapi.api.dsl.feeder.JavaFeederSupport.csv;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.status;
import static com.vgalloy.gatlingjavaapi.api.dsl.loop.JavaLoopSupport.repeat;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.SourceFeederBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class AdvancedSimulationStep05 extends SimulationWrapper {

  private static class Search {

    private static SourceFeederBuilderWrapper feeder = csv("search.csv").random();

    private static ChainBuilderWrapper search =
        exec(http("Home").get("/"))
            .pause(1, TimeUnit.SECONDS)
            .feed(feeder)
            .exec(
                http("Search")
                    .get("/computers?f=${searchCriterion}")
                    .check(
                        css("a:contains('${searchComputerName}')", "href").saveAs("computerURL")))
            .pause(1, TimeUnit.SECONDS)
            .exec(http("Select").get("${computerURL}").check(status().is(200)))
            .pause(1, TimeUnit.SECONDS);
  }

  private static class Browse {
    private static ChainBuilderWrapper browse =
        repeat(4, "i", exec(http("Page ${i}").get("/computers?p=${i}")).pause(1, TimeUnit.SECONDS));
  }

  private static class Edit {

    private static Map<String, String> headers_10 = new HashMap<>();

    static {
      headers_10.put("Content-Type", "application/x-www-form-urlencoded");
    }

    private static ChainBuilderWrapper edit =
        tryMax(
                2,
                exec(http("Form").get("/computers/new"))
                    .pause(1, TimeUnit.SECONDS)
                    .exec(
                        http("Post")
                            .post("/computers")
                            .headers(headers_10)
                            .formParam("name", "Beautiful Computer")
                            .formParam("introduced", "2012-05-30")
                            .formParam("discontinued", "")
                            .formParam("company", "37")
                            .check(
                                status()
                                    .is(session -> 200 + ThreadLocalRandom.current().nextInt(2)))))
            .exitHereIfFailed();
  }

  @Override
  protected void configure() {
    HttpProtocolBuilderWrapper httpConf =
        http()
            .baseURL("http://localhost:8888")
            .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .doNotTrackHeader("1")
            .acceptLanguageHeader("en-US,en;q=0.5")
            .acceptEncodingHeader("gzip, deflate")
            .userAgentHeader(
                "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0");

    ScenarioBuilderWrapper users = scenario("Users").exec(Search.search, Browse.browse);
    ScenarioBuilderWrapper admins =
        scenario("Admins").exec(Search.search, Browse.browse, Edit.edit);

    setUp(
            users.inject(rampUsers(10, 10, TimeUnit.MILLISECONDS)),
            admins.inject(rampUsers(2, 10, TimeUnit.MILLISECONDS)))
        .protocols(httpConf);
  }
}
