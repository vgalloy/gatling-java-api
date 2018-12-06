package com.vgalloy.gatlingjavaapi.api.dsl.feeder;

import java.util.Objects;

import io.gatling.core.Predef;
import io.gatling.core.feeder.SourceFeederBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.SourceFeederBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaFeederSupport {

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaFeederSupport() {
        throw new AssertionError("No instance of JavaFeederSupport");
    }

    public static SourceFeederBuilderWrapper csv(final String fileName) {
        Objects.requireNonNull(fileName, "fileName");

        SourceFeederBuilder<String> recordSeqFeederBuilder = Predef.csv(fileName, '\"', GatlingConfigurationSupplier.GATLING_CONFIGURATION);
        return new SourceFeederBuilderWrapper(recordSeqFeederBuilder);
    }
}
