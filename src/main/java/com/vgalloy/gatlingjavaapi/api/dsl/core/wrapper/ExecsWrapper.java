package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import io.gatling.core.structure.StructureBuilder;

import java.util.function.Supplier;

/**
 * @author Vincent Galloy.
 *         Created by Vincent Galloy on 28/02/2017.
 */
public interface ExecsWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends Supplier<STRUCTURE> {

	WRAPPER newInstance(STRUCTURE newStructure);
}
