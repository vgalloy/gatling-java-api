package com.vgalloy.gatlingjavaapi.api.dsl.feeder;

import java.util.Objects;

import io.gatling.core.Predef;
import io.gatling.core.feeder.RecordSeqFeederBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.RecordSeqFeederBuilderWrapper;
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
        throw new AssertionError();
    }

    public static RecordSeqFeederBuilderWrapper csv(String fileName) {
        Objects.requireNonNull(fileName);

        RecordSeqFeederBuilder<String> recordSeqFeederBuilder = Predef.csv(fileName, '\"', (char) 0, GatlingConfigurationSupplier.GATLING_CONFIGURATION);
        return new RecordSeqFeederBuilderWrapper(recordSeqFeederBuilder);
    }
}
