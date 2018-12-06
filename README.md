# Gatling java api
## Build
```
maven clean install
```
Or
```
./gradlew build
```

## Use
```java
package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingResultAnalyzer;
import com.vgalloy.gatlingjavaapi.api.service.JavaGatlingRunner;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import com.vgalloy.gatlingjavaapi.api.service.SimulationResult;
import io.gatling.app.RunResult;

import static com.vgalloy.gatlingjavaapi.api.dsl.assertion.JavaAssertionSupport.global;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.exec;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaCoreDsl.scenario;
import static com.vgalloy.gatlingjavaapi.api.dsl.core.JavaInjectionSupport.atOnceUsers;
import static com.vgalloy.gatlingjavaapi.api.dsl.http.JavaHttpDsl.http;

public final class Main {

    public static void main(String[] args) {
        JavaGatlingRunner javaGatlingRunner = JavaGatlingRunner.getInstance();
        JavaGatlingResultAnalyzer javaGatlingResultAnalyzer = JavaGatlingResultAnalyzer.getInstance();

        ScenarioBuilderWrapper scn = scenario("MyScenario")
            .exec(http("request_1")
                .get("/home"))
            .repeat(2,
                exec(http("request_get")
                    .get("/get/1")
                ));
        HttpProtocolBuilderWrapper httpConf = http()
            .baseURL("http://localhost:" + 8080);

        JavaSimulation javaSimulation = JavaSimulation.builder()
            .scenario(
                scn.inject(atOnceUsers(2))
            )
            .protocols(httpConf)
            .assertion(
                global().responseTime().mean().lt(1_000),
                global().successfulRequests().percent().gt(99.9d)
            )
            .build();

        // Run the simulation
        RunResult runResult = javaGatlingRunner.run(javaSimulation);

        // Assert everything is ok
        SimulationResult simulationResult = javaGatlingResultAnalyzer.load(runResult);
        if (!simulationResult.isSuccess()) {
            throw new IllegalStateException("Run fails");
        }

        // Generate HTML report
        javaGatlingResultAnalyzer.generateHtml(runResult);
    }
}
```

