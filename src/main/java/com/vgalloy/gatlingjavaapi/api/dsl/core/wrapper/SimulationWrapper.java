package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.PopulationBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.commons.stats.assertion.Assertion;
import io.gatling.core.protocol.Protocol;
import io.gatling.core.scenario.Simulation;
import io.gatling.core.structure.PopulationBuilder;
import io.gatling.http.protocol.HttpProtocolBuilder;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public abstract class SimulationWrapper extends Simulation {

    public SimulationWrapper() {
        configure();
    }

    protected abstract void configure();

    protected SetUpWrapper setUp(PopulationBuilderWrapper... populationBuilderWrappers) {
        Objects.requireNonNull(populationBuilderWrappers);

        scala.collection.immutable.List<PopulationBuilder> result = Arrays.stream(populationBuilderWrappers)
            .map(PopulationBuilderWrapper::get)
            .collect(ScalaHelper.toScalaList());
        return new SetUpWrapper(setUp(result));
    }

    protected final class SetUpWrapper {

        private final SetUp setUp;

        private SetUpWrapper(SetUp setUp) {
            this.setUp = setUp;
        }

        public SetUpWrapper protocols(HttpProtocolBuilderWrapper... httpProtocolBuilderWrapper) {
            Objects.requireNonNull(httpProtocolBuilderWrapper);

            scala.collection.immutable.List<Protocol> result = Arrays.stream(httpProtocolBuilderWrapper)
                .map(HttpProtocolBuilderWrapper::get)
                .map(HttpProtocolBuilder::build)
                .collect(ScalaHelper.toScalaList());
            return new SetUpWrapper(setUp.protocols(result));
        }

        public SetUpWrapper assertion(Assertion... assertions) {
            Objects.requireNonNull(assertions);

            return new SetUpWrapper(setUp.assertions(ScalaHelper.map(assertions)));
        }
    }
}
