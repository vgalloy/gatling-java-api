package com.vgalloy.gatlingjavaapi.api.dsl.core;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ScenarioBuilderWrapper;
import io.gatling.core.Predef;
import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.ScenarioBuilder;
import scala.collection.immutable.List$;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaCoreDsl {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private JavaCoreDsl() {
        throw new AssertionError("No instance of JavaCoreDsl");
    }

    public static ScenarioBuilderWrapper scenario(String scenarioName) {
        return new ScenarioBuilderWrapper(new ScenarioBuilder(scenarioName, List$.MODULE$.empty()));
    }

    public static ChainBuilderWrapper exec(ActionBuilderSupplier actionBuilderSupplier) {
        return new ChainBuilderWrapper((ChainBuilder) Predef.exec(actionBuilderSupplier.toActionBuilder()));
    }
}
