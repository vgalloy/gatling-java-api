package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import java.util.function.Supplier;

import io.gatling.core.structure.StructureBuilder;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface ExecsWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends Supplier<STRUCTURE> {

    WRAPPER newInstance(STRUCTURE newStructure);
}
