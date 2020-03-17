package com.vgalloy.gatlingjavaapi.api.dsl.feeder;

import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.SourceFeederBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import io.gatling.core.Predef;
import io.gatling.core.feeder.BatchableFeederBuilder;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaFeederSupport {

  /** Constructor. To prevent external instantiation */
  private JavaFeederSupport() {
    throw new AssertionError("No instance of JavaFeederSupport");
  }

  public static SourceFeederBuilderWrapper<BatchableFeederBuilder<String>> csv(
      final String fileName) {
    Objects.requireNonNull(fileName, "fileName");

    final BatchableFeederBuilder<String> recordSeqFeederBuilder =
        Predef.csv(fileName, '\"', GatlingConfigurationSupplier.GATLING_CONFIGURATION);
    return new SourceFeederBuilderWrapper<>(recordSeqFeederBuilder);
  }
}
