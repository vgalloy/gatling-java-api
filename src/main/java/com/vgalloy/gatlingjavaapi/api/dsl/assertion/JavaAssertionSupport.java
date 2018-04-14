package com.vgalloy.gatlingjavaapi.api.dsl.assertion;

import io.gatling.core.Predef;
import io.gatling.core.assertion.AssertionWithPath;

import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;

/**
 * Created by Vincent Galloy on 27/02/2017.
 *
 * @author Vincent Galloy.
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
