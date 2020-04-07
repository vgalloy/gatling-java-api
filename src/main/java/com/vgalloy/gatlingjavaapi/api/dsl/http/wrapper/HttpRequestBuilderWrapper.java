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
package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.CheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ActionBuilderSupplier;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.action.builder.ActionBuilder;
import io.gatling.core.body.Body;
import io.gatling.core.body.StringBody;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.http.Predef;
import io.gatling.http.action.HttpRequestActionBuilder;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.request.builder.HttpRequestBuilder;
import io.gatling.http.response.Response;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpRequestBuilderWrapper
    extends RequestBuilderWrapper<HttpRequestBuilder, HttpRequestBuilderWrapper>
    implements ActionBuilderSupplier {

  private final HttpRequestBuilder httpRequestBuilder;

  public HttpRequestBuilderWrapper(HttpRequestBuilder httpRequestBuilder) {
    this.httpRequestBuilder = Objects.requireNonNull(httpRequestBuilder);
  }

  @Override
  public HttpRequestBuilder get() {
    return httpRequestBuilder;
  }

  @Override
  protected HttpRequestBuilderWrapper newInstance(HttpRequestBuilder newStructure) {
    return new HttpRequestBuilderWrapper(newStructure);
  }

  public HttpRequestBuilderWrapper formParam(String name, Object value) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(value);

    return newInstance(httpRequestBuilder.formParam(Expression.of(name), Expression.of(value)));
  }

  public HttpRequestBuilderWrapper body(String body) {
    Objects.requireNonNull(body);

    Body stringBody =
        new StringBody(Expression.of(body), GatlingConfigurationSupplier.GATLING_CONFIGURATION);
    return newInstance(httpRequestBuilder.body(stringBody));
  }

  public <A, T> HttpRequestBuilderWrapper check(
      final CheckBuilderWrapper<A, T, ?> checkBuilderWrapper) {
    Objects.requireNonNull(checkBuilderWrapper, "checkBuilderWrapper");

    final CheckBuilder<A, T, ?> checkBuilder = checkBuilderWrapper.get();
    final CheckMaterializer<A, HttpCheck, Response, T> materializer =
        checkBuilderWrapper.getMaterializer();
    final HttpCheck httpCheck = Predef.checkBuilder2HttpCheck(checkBuilder, materializer);
    return check(httpCheck);
  }

  public HttpRequestBuilderWrapper check(final HttpCheck httpCheck) {
    Objects.requireNonNull(httpCheck, "httpCheck");

    return newInstance(httpRequestBuilder.check(ScalaHelper.map(httpCheck)));
  }

  @Override
  public ActionBuilder toActionBuilder() {
    return new HttpRequestActionBuilder(httpRequestBuilder);
  }
}
