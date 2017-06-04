package com.vgalloy.gatlingjavaapi.internal.impl;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.SimulationWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class InternalSimulationWrapper extends SimulationWrapper {

    static JavaSimulation javaSimulation;

    @Override
    protected void configure() {
        setUp(javaSimulation.getPopulationBuilderWrappers())
                .protocols(javaSimulation.getHttpProtocolBuilderWrapper())
                .assertion(javaSimulation.getAssertions());
    }
}
