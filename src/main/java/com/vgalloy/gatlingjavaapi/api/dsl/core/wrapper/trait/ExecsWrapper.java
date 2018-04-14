package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import java.util.function.Supplier;

import io.gatling.core.structure.StructureBuilder;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @param <STRUCTURE> The gatling structure wrapped in the wrapper
 * @param <WRAPPER>   the wrapper it self
 * @author Vincent Galloy.
 */
public interface ExecsWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends Supplier<STRUCTURE> {

    /**
     * Build a new instance of the wrapper
     *
     * @param newStructure the wrapped gatling structure
     * @return the new wrapper
     */
    WRAPPER newInstance(STRUCTURE newStructure);
}
