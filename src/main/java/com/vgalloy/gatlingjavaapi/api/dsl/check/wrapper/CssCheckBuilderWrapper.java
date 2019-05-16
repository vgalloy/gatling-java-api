package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.Predef;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.ValidatorCheckBuilder;
import io.gatling.core.check.extractor.CriterionExtractor;
import io.gatling.core.check.extractor.css.CssCheckBuilder;
import io.gatling.core.check.extractor.css.CssCheckType;
import java.util.Objects;
import java.util.function.Supplier;
import jodd.lagarto.dom.NodeSelector;
import scala.Option;
import scala.Tuple2;
import scala.collection.Seq;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class CssCheckBuilderWrapper
    implements Supplier<CssCheckBuilder<String>>,
        SaveAsWrapper<CssCheckType, NodeSelector, String> {

  private final CssCheckBuilder<String> cssCheckBuilder;

  public CssCheckBuilderWrapper(CssCheckBuilder<String> cssCheckBuilder) {
    this.cssCheckBuilder = Objects.requireNonNull(cssCheckBuilder);
  }

  @Override
  public CssCheckBuilder<String> get() {
    return cssCheckBuilder;
  }

  public JavaExtractor<String> findExtractor(int occurrence) {
    return (JavaExtractor<String>) cssCheckBuilder.findExtractor(occurrence);
  }

  public JavaExtractor<Seq<String>> findAllExtractor() {
    return (JavaExtractor<Seq<String>>) cssCheckBuilder.findAllExtractor();
  }

  public JavaExtractor<Object> countExtractor() {
    return (JavaExtractor<Object>) cssCheckBuilder.countExtractor();
  }

  @Override
  public CheckBuilder<CssCheckType, NodeSelector, String> toCheckBuilder() {
    ValidatorCheckBuilder<CssCheckType, NodeSelector, String> validatorCheckBuilder =
        cssCheckBuilder.find();
    return Predef.validatorCheckBuilder2CheckBuilder(validatorCheckBuilder);
  }

  @Override
  public CheckMaterializer checkMaterializer() {
    return io.gatling.http.Predef.httpBodyCssCheckMaterializer(cssCheckBuilder.selectors());
  }

  public interface JavaExtractor<T>
      extends Expression<CriterionExtractor<NodeSelector, Tuple2<String, Option<String>>, T>> {}
}
