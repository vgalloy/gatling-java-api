package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import java.util.Objects;
import java.util.function.Supplier;

import io.gatling.core.structure.PopulationBuilder;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class PopulationBuilderWrapper implements Supplier<PopulationBuilder> {

    private final PopulationBuilder populationBuilder;

    public PopulationBuilderWrapper(PopulationBuilder populationBuilder) {
        this.populationBuilder = Objects.requireNonNull(populationBuilder);
    }

    @Override
    public PopulationBuilder get() {
        return populationBuilder;
    }
}
