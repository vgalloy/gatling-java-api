package com.vgalloy.gatlingjavaapi.api.dsl.core;

import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import io.gatling.core.Predef;
import io.gatling.core.assertion.AssertionWithPath;

/**
 * @author Vincent Galloy.
 *         Created by Vincent Galloy on 27/02/2017.
 */
public final class JavaAssertionSupport {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private JavaAssertionSupport() {
        throw new AssertionError();
    }

    public static AssertionWithPath global() {
        return Predef.global(GatlingConfigurationSupplier.GATLING_CONFIGURATION);
    }
}
