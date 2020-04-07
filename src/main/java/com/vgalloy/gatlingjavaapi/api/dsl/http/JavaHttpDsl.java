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
package com.vgalloy.gatlingjavaapi.api.dsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.FindCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.WsWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.FindCheckBuilder;
import io.gatling.core.session.SessionPrivateAttributes;
import io.gatling.http.Predef;
import io.gatling.http.action.ws.Ws;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.check.status.HttpStatusCheckType;
import io.gatling.http.check.ws.WsBinaryFrameCheck;
import io.gatling.http.check.ws.WsTextFrameCheck;
import io.gatling.http.request.builder.Http;
import io.gatling.http.response.Response;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaHttpDsl {

  /** Constructor. To prevent external instantiation */
  private JavaHttpDsl() {
    throw new AssertionError("No instance of JavaHttpDsl");
  }

  public static HttpWrapper http(String requestName) {
    final Expression<String> requestNameExpression = Expression.of(requestName);
    return new HttpWrapper(new Http(requestNameExpression));
  }

  public static HttpProtocolBuilderWrapper http() {
    return new HttpProtocolBuilderWrapper(
        Predef.http(GatlingConfigurationSupplier.GATLING_CONFIGURATION));
  }

  public static WsWrapper ws(String requestName) {
    final Expression<String> requestNameExpression = Expression.of(requestName);
    final Ws ws =
        Predef.ws()
            .apply(
                requestNameExpression,
                SessionPrivateAttributes.PrivateAttributePrefix() + "http.webSocket");
    return new WsWrapper(ws);
  }

  public static WsWrapper ws(String requestName, String wsName) {
    final Expression<String> requestNameExpression = Expression.of(requestName);
    return new WsWrapper(new Ws(requestNameExpression, wsName));
  }

  public static WsBinaryFrameCheck checkBinaryMessage(final String name) {
    Objects.requireNonNull(name, "name");
    return Predef.ws().checkBinaryMessage(name);
  }

  public static WsTextFrameCheck checkTextMessage(final String name) {
    Objects.requireNonNull(name, "name");
    return Predef.ws().checkTextMessage(name);
  }

  public static FindCheckBuilderWrapper<
          HttpStatusCheckType,
          Response,
          Integer,
          FindCheckBuilder<HttpStatusCheckType, Response, Integer>>
      status() {
    @SuppressWarnings({"unchecked", "rawtype"})
    final FindCheckBuilder<HttpStatusCheckType, Response, Integer> status =
        (FindCheckBuilder) Predef.status();
    return new FindCheckBuilderWrapper<
        HttpStatusCheckType,
        Response,
        Integer,
        FindCheckBuilder<HttpStatusCheckType, Response, Integer>>() {

      @Override
      public FindCheckBuilder<HttpStatusCheckType, Response, Integer> get() {
        return status;
      }

      @Override
      public CheckMaterializer<HttpStatusCheckType, HttpCheck, Response, Response>
          getMaterializer() {
        return Predef.httpStatusCheckMaterializer();
      }
    };
  }
}
