package com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper;

import io.gatling.core.feeder.FeederBuilderBase;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class SourceFeederBuilderWrapper<T extends FeederBuilderBase<String>>
    implements Supplier<T> {

  private final T instance;

  public SourceFeederBuilderWrapper(final T instance) {
    this.instance = Objects.requireNonNull(instance, "instance");
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> queue() {
    return new SourceFeederBuilderWrapper<>(instance.queue());
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> random() {
    return new SourceFeederBuilderWrapper<>(instance.random());
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> shuffle() {
    return new SourceFeederBuilderWrapper<>(instance.shuffle());
  }

  public SourceFeederBuilderWrapper<FeederBuilderBase<String>> circular() {
    return new SourceFeederBuilderWrapper<>(instance.circular());
  }

  @Override
  public T get() {
    return instance;
  }
}
