package com.vgalloy.gatlingjavaapi.api.service;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.PopulationBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import io.gatling.commons.stats.assertion.Assertion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaSimulation {

	private final PopulationBuilderWrapper[] populationBuilderWrappers;
	private final HttpProtocolBuilderWrapper[] httpProtocolBuilderWrappers;
	private final Assertion[] assertions;

	private JavaSimulation(List<PopulationBuilderWrapper> populationBuilderWrappers, List<HttpProtocolBuilderWrapper> httpProtocolBuilderWrappers, List<Assertion> assertions) {
		Objects.requireNonNull(populationBuilderWrappers);
		Objects.requireNonNull(httpProtocolBuilderWrappers);
		Objects.requireNonNull(assertions);

		this.populationBuilderWrappers = populationBuilderWrappers.toArray(new PopulationBuilderWrapper[populationBuilderWrappers.size()]);
		this.httpProtocolBuilderWrappers = httpProtocolBuilderWrappers.toArray(new HttpProtocolBuilderWrapper[httpProtocolBuilderWrappers.size()]);
		this.assertions = assertions.toArray(new Assertion[assertions.size()]);
	}

	public PopulationBuilderWrapper[] getPopulationBuilderWrappers() {
		return populationBuilderWrappers;
	}

	public HttpProtocolBuilderWrapper[] getHttpProtocolBuilderWrapper() {
		return httpProtocolBuilderWrappers;
	}

	public Assertion[] getAssertions() {
		return assertions;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		private final List<PopulationBuilderWrapper> populationBuilderWrappers = new ArrayList<>();
		private final List<HttpProtocolBuilderWrapper> httpProtocolBuilderWrappers = new ArrayList<>();
		private final List<Assertion> assertions = new ArrayList<>();

		public Builder scenario(PopulationBuilderWrapper... populationBuilderWrappers) {
			Objects.requireNonNull(populationBuilderWrappers);
			this.populationBuilderWrappers.addAll(Arrays.asList(populationBuilderWrappers));
			return this;
		}

		public Builder protocols(HttpProtocolBuilderWrapper... httpProtocolBuilderWrapper) {
			Objects.requireNonNull(httpProtocolBuilderWrapper);
			this.httpProtocolBuilderWrappers.addAll(Arrays.asList(httpProtocolBuilderWrapper));
			return this;
		}

		public Builder assertion(Assertion... assertions) {
			Objects.requireNonNull(assertions);
			this.assertions.addAll(Arrays.asList(assertions));
			return this;
		}

		public JavaSimulation build() {
			return new JavaSimulation(populationBuilderWrappers, httpProtocolBuilderWrappers, assertions);
		}
	}
}
