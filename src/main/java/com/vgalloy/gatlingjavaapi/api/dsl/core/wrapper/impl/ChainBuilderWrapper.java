package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl;

import java.util.Objects;

import io.gatling.core.structure.ChainBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait.StructureBuilderWrapper;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ChainBuilderWrapper implements StructureBuilderWrapper<ChainBuilder, ChainBuilderWrapper> {

    private final ChainBuilder chainBuilder;

    public ChainBuilderWrapper(ChainBuilder chainBuilder) {
        this.chainBuilder = Objects.requireNonNull(chainBuilder);
    }

    @Override
    public ChainBuilderWrapper newInstance(ChainBuilder newStructure) {
        return new ChainBuilderWrapper(newStructure);
    }

    @Override
    public ChainBuilder get() {
        return chainBuilder;
    }
}
