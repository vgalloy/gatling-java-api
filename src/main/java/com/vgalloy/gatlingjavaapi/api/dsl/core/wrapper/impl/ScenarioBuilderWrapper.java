package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl;

import java.util.Objects;

import io.gatling.core.Predef;
import io.gatling.core.controller.inject.open.OpenInjectionStep;
import io.gatling.core.structure.ScenarioBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait.StructureBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ScenarioBuilderWrapper implements StructureBuilderWrapper<ScenarioBuilder, ScenarioBuilderWrapper> {

    private final ScenarioBuilder scenarioBuilder;

    public ScenarioBuilderWrapper(ScenarioBuilder scenarioBuilder) {
        this.scenarioBuilder = Objects.requireNonNull(scenarioBuilder);
    }

    @Override
    public ScenarioBuilderWrapper newInstance(ScenarioBuilder newStructure) {
        return new ScenarioBuilderWrapper(newStructure);
    }

    @Override
    public ScenarioBuilder get() {
        return scenarioBuilder;
    }

    public PopulationBuilderWrapper inject(OpenInjectionStep injectionProfileFactory, OpenInjectionStep... injectionProfileFactories) {
        Objects.requireNonNull(injectionProfileFactories);
        return new PopulationBuilderWrapper(scenarioBuilder.inject(injectionProfileFactory, ScalaHelper.map(injectionProfileFactories), Predef.openInjectionProfileFactory()));
    }
}
