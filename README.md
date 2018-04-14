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

