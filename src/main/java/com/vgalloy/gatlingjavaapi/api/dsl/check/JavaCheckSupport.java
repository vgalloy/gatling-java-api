package com.vgalloy.gatlingjavaapi.api.dsl.check;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.CssCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.Predef;
import io.gatling.core.check.extractor.css.CssCheckBuilder;
import io.gatling.core.check.extractor.css.CssSelectors;
import io.gatling.core.check.extractor.jsonpath.JsonPathCheckBuilder;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaCheckSupport {

  /** Constructor. To prevent instantiation */
  private JavaCheckSupport() {
    throw new AssertionError("No instance of JavaCheckSupport");
  }

  public static CssCheckBuilderWrapper css(String selector, String nodeAttribute) {
    Objects.requireNonNull(selector);
    Objects.requireNonNull(nodeAttribute);

    CssSelectors cssExtractor = io.gatling.core.Predef.defaultCssSelectors();
    final CssCheckBuilder<String> cssCheckBuilder =
        Predef.css(Expression.of(selector), nodeAttribute, cssExtractor);
    return new CssCheckBuilderWrapper(cssCheckBuilder);
  }

  public static JsonPathCheckBuilder<String> jsonPath(final String path) {
    return JsonPathCheckBuilder.jsonPath(Expression.of(path), Predef.defaultJsonPaths());
  }
}
