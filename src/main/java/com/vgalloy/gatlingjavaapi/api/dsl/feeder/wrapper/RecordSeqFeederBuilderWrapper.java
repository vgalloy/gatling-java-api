package com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper;

import java.util.Objects;
import java.util.function.Supplier;

import io.gatling.core.feeder.RecordSeqFeederBuilder;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class RecordSeqFeederBuilderWrapper implements Supplier<RecordSeqFeederBuilder<String>> {

    private final RecordSeqFeederBuilder<String> recordSeqFeederBuilder;

    public RecordSeqFeederBuilderWrapper(RecordSeqFeederBuilder<String> recordSeqFeederBuilder) {
        this.recordSeqFeederBuilder = Objects.requireNonNull(recordSeqFeederBuilder);
    }

    public RecordSeqFeederBuilderWrapper queue() {
        return new RecordSeqFeederBuilderWrapper(recordSeqFeederBuilder.queue());
    }

    public RecordSeqFeederBuilderWrapper random() {
        return new RecordSeqFeederBuilderWrapper(recordSeqFeederBuilder.random());
    }

    public RecordSeqFeederBuilderWrapper shuffle() {
        return new RecordSeqFeederBuilderWrapper(recordSeqFeederBuilder.shuffle());
    }

    public RecordSeqFeederBuilderWrapper circular() {
        return new RecordSeqFeederBuilderWrapper(recordSeqFeederBuilder.circular());
    }

    @Override
    public RecordSeqFeederBuilder<String> get() {
        return recordSeqFeederBuilder;
    }
}
