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

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.http.request.builder.Http;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpWrapper {

  private final Http http;

  public HttpWrapper(Http http) {
    this.http = Objects.requireNonNull(http);
  }

  public HttpRequestBuilderWrapper get(String requestUrl) {
    Objects.requireNonNull(requestUrl);

    Expression<String> requestExpression = Expression.of(requestUrl);
    return new HttpRequestBuilderWrapper(http.get(requestExpression));
  }

  public HttpRequestBuilderWrapper put(String requestUrl) {
    Objects.requireNonNull(requestUrl);

    Expression<String> requestExpression = Expression.of(requestUrl);
    return new HttpRequestBuilderWrapper(http.put(requestExpression));
  }

  public HttpRequestBuilderWrapper post(String requestUrl) {
    Objects.requireNonNull(requestUrl);

    Expression<String> requestExpression = Expression.of(requestUrl);
    return new HttpRequestBuilderWrapper(http.post(requestExpression));
  }

  public HttpRequestBuilderWrapper head(String requestUrl) {
    Objects.requireNonNull(requestUrl);

    Expression<String> requestExpression = Expression.of(requestUrl);
    return new HttpRequestBuilderWrapper(http.head(requestExpression));
  }

  public HttpRequestBuilderWrapper patch(String requestUrl) {
    Objects.requireNonNull(requestUrl);

    Expression<String> requestExpression = Expression.of(requestUrl);
    return new HttpRequestBuilderWrapper(http.patch(requestExpression));
  }

  public HttpRequestBuilderWrapper delete(String requestUrl) {
    Objects.requireNonNull(requestUrl);

    Expression<String> requestExpression = Expression.of(requestUrl);
    return new HttpRequestBuilderWrapper(http.delete(requestExpression));
  }
}
