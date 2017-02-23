package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import io.gatling.core.structure.ChainBuilder;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public class StructureSupportWrapper implements StructureBuilderWrapper<ChainBuilder, StructureSupportWrapper> {

	private final ChainBuilder chainBuilder;

	public StructureSupportWrapper(ChainBuilder chainBuilder) {
		this.chainBuilder = Objects.requireNonNull(chainBuilder);
	}

	@Override
	public StructureSupportWrapper newInstance(ChainBuilder newStructure) {
		return new StructureSupportWrapper(newStructure);
	}

	@Override
	public ChainBuilder get() {
		return chainBuilder;
	}
}
