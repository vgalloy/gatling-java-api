package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.PopulationBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import io.gatling.commons.stats.assertion.Assertion;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class DslTest {

    @Test(expected = NullPointerException.class)
    public void nullInjector() {
        // WHEN
        JavaSimulation.builder().scenario((PopulationBuilderWrapper[]) null);
    }

    @Test(expected = NullPointerException.class)
    public void nullProtocol() {
        // WHEN
        JavaSimulation.builder().protocols((HttpProtocolBuilderWrapper[]) null);
    }

    @Test(expected = NullPointerException.class)
    public void nullAssertion() {
        // WHEN
        JavaSimulation.builder().assertion((Assertion[]) null);
    }

    @Test
    public void nullValue() {
        // WHEN
        JavaSimulation.builder()
                .scenario()
                .protocols()
                .assertion();
    }
}
