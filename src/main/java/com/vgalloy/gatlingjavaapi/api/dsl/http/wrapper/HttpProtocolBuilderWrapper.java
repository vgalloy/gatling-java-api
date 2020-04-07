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
import io.gatling.http.protocol.HttpProtocolBuilder;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class HttpProtocolBuilderWrapper implements Supplier<HttpProtocolBuilder> {

  private final HttpProtocolBuilder httpProtocolBuilder;

  public HttpProtocolBuilderWrapper(HttpProtocolBuilder httpProtocolBuilder) {
    this.httpProtocolBuilder = Objects.requireNonNull(httpProtocolBuilder);
  }

  @Override
  public HttpProtocolBuilder get() {
    return httpProtocolBuilder;
  }

  public HttpProtocolBuilderWrapper baseURL(String baseUrl) {
    return new HttpProtocolBuilderWrapper(httpProtocolBuilder.baseUrl(baseUrl));
  }

  public HttpProtocolBuilderWrapper acceptHeader(String acceptHeader) {
    return new HttpProtocolBuilderWrapper(
        httpProtocolBuilder.acceptHeader(Expression.of(acceptHeader)));
  }

  public HttpProtocolBuilderWrapper doNotTrackHeader(String doNotTrackHeader) {
    return new HttpProtocolBuilderWrapper(
        httpProtocolBuilder.doNotTrackHeader(Expression.of(doNotTrackHeader)));
  }

  public HttpProtocolBuilderWrapper acceptLanguageHeader(String acceptLanguageHeader) {
    return new HttpProtocolBuilderWrapper(
        httpProtocolBuilder.acceptLanguageHeader(Expression.of(acceptLanguageHeader)));
  }

  public HttpProtocolBuilderWrapper acceptEncodingHeader(String acceptEncodingHeader) {
    return new HttpProtocolBuilderWrapper(
        httpProtocolBuilder.acceptEncodingHeader(Expression.of(acceptEncodingHeader)));
  }

  public HttpProtocolBuilderWrapper userAgentHeader(String userAgentHeader) {
    return new HttpProtocolBuilderWrapper(
        httpProtocolBuilder.userAgentHeader(Expression.of(userAgentHeader)));
  }
}
