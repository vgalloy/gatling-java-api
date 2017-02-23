package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.core.controller.inject.InjectionStep;
import io.gatling.core.structure.ScenarioBuilder;

import java.util.Objects;

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

	public PopulationBuilderWrapper inject(InjectionStep... injectionStep) {
		Objects.requireNonNull(injectionStep);

		return new PopulationBuilderWrapper(scenarioBuilder.inject(ScalaHelper.map(injectionStep)));
	}
}
