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
package com.vgalloy.gatlingjavaapi.api.dsl.core;

import io.gatling.core.Predef;
import io.gatling.core.controller.inject.open.OpenInjectionStep;
import java.util.concurrent.TimeUnit;
import scala.concurrent.duration.FiniteDuration;

/**
 * Created by Vincent Galloy on 27/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaInjectionSupport {

  /** Constructor. To prevent instantiation */
  private JavaInjectionSupport() {
    throw new AssertionError("No instance of JavaInjectionSupport");
  }

  public static OpenInjectionStep nothingFor(long length, TimeUnit unit) {
    FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    return Predef.nothingFor(finiteDuration);
  }

  public static OpenInjectionStep atOnceUsers(int i) {
    return Predef.atOnceUsers(i);
  }

  public static OpenInjectionStep rampUsers(int users, long length, TimeUnit unit) {
    FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    return Predef.rampUsers(users).during(finiteDuration);
  }

  public static OpenInjectionStep constantUsersPerSec(double users, long length, TimeUnit unit) {
    FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    return Predef.constantUsersPerSec(users).during(finiteDuration);
  }

  public static OpenInjectionStep rampUsersPerSec(
      double rate1, double rate2, long length, TimeUnit unit) {
    FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    return Predef.rampUsersPerSec(rate1).to(rate2).during(finiteDuration);
  }

  public static OpenInjectionStep heavisideUsers(int user, long length, TimeUnit unit) {
    FiniteDuration finiteDuration = FiniteDuration.apply(length, unit);
    return Predef.heavisideUsers(user).during(finiteDuration);
  }
}
