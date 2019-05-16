package com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper;

import io.gatling.core.feeder.SourceFeederBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class SourceFeederBuilderWrapper implements Supplier<SourceFeederBuilder<String>> {

  private final SourceFeederBuilder<String> recordSeqFeederBuilder;

  public SourceFeederBuilderWrapper(SourceFeederBuilder<String> recordSeqFeederBuilder) {
    this.recordSeqFeederBuilder = Objects.requireNonNull(recordSeqFeederBuilder);
  }

  public SourceFeederBuilderWrapper queue() {
    return new SourceFeederBuilderWrapper(recordSeqFeederBuilder.queue());
  }

  public SourceFeederBuilderWrapper random() {
    return new SourceFeederBuilderWrapper(recordSeqFeederBuilder.random());
  }

  public SourceFeederBuilderWrapper shuffle() {
    return new SourceFeederBuilderWrapper(recordSeqFeederBuilder.shuffle());
  }

  public SourceFeederBuilderWrapper circular() {
    return new SourceFeederBuilderWrapper(recordSeqFeederBuilder.circular());
  }

  @Override
  public SourceFeederBuilder<String> get() {
    return recordSeqFeederBuilder;
  }
}
