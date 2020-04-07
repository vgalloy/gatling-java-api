/*
 * Copyright 2020 Vincent Galloy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vgalloy.gatlingjavaapi.api.dsl.check;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.MultipleFindCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.Predef;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.MultipleFindCheckBuilder;
import io.gatling.core.check.css.CssCheckType;
import io.gatling.core.check.css.CssSelectors;
import io.gatling.core.check.jsonpath.JsonPathCheckBuilder;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;
import java.util.Objects;
import jodd.lagarto.dom.NodeSelector;

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

  public static MultipleFindCheckBuilderWrapper<CssCheckType, NodeSelector, String> css(
      final String selector, final String nodeAttribute) {
    Objects.requireNonNull(selector, "selector");
    Objects.requireNonNull(nodeAttribute, "nodeAttribute");

    final CssSelectors cssSelectors = io.gatling.core.Predef.defaultCssSelectors();
    final MultipleFindCheckBuilder<CssCheckType, NodeSelector, String> multipleFindCheckBuilder =
        Predef.css(Expression.of(selector), nodeAttribute, cssSelectors);
    final CheckMaterializer<CssCheckType, HttpCheck, Response, NodeSelector> materializer =
        io.gatling.http.Predef.httpBodyCssCheckMaterializer(cssSelectors);
    return new MultipleFindCheckBuilderWrapper<>(multipleFindCheckBuilder, materializer);
  }

  public static JsonPathCheckBuilder<String> jsonPath(final String path) {
    return JsonPathCheckBuilder.jsonPath(Expression.of(path), Predef.defaultJsonPaths());
  }
}
