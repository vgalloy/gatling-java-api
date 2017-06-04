package com.vgalloy.gatlingjavaapi.api.dsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.ValidatorCheckBuilderWrapper;

/**
 * Created by Vincent Galloy on 04/06/17.
 *
 * @author Vincent Galloy
 */
public final class JavaHttpCheckSupport {

    public static final ValidatorCheckBuilderWrapper status = new ValidatorCheckBuilderWrapper();

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaHttpCheckSupport() {
        throw new AssertionError();
    }
}
