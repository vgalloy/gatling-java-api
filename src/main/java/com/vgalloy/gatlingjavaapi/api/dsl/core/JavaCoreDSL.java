package com.vgalloy.gatlingjavaapi.api.dsl.core;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.HttpRequestBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.ScenarioBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.StructureSupportWrapper;
import io.gatling.core.Predef;
import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.ScenarioBuilder;
import io.gatling.http.action.sync.HttpRequestActionBuilder;
import scala.collection.immutable.List$;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaCoreDSL {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private JavaCoreDSL() {
        throw new AssertionError();
    }

    public static ScenarioBuilderWrapper scenario(String scenarioName) {
        return new ScenarioBuilderWrapper(new ScenarioBuilder(scenarioName, List$.MODULE$.empty()));
    }

    public static StructureSupportWrapper exec(HttpRequestBuilderWrapper httpRequestBuilderWrapper) {
        return new StructureSupportWrapper((ChainBuilder) Predef.exec(new HttpRequestActionBuilder(httpRequestBuilderWrapper.get())));
    }
}
