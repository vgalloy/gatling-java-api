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
package com.vgalloy.gatlingjavaapi.test;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.PopulationBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.service.JavaSimulation;
import io.gatling.commons.stats.assertion.Assertion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
final class DslTest {

  @Test
  public void nullInjector() {
    // GIVEN
    final JavaSimulation.Builder builder = JavaSimulation.builder();

    // WHEN
    final NullPointerException exception =
        Assertions.assertThrows(
            NullPointerException.class, () -> builder.scenario((PopulationBuilderWrapper[]) null));

    // THEN
    Assertions.assertEquals(exception.getMessage(), "populationBuilderWrappers");
  }

  @Test
  public void nullProtocol() {
    // GIVEN
    final JavaSimulation.Builder builder = JavaSimulation.builder();

    // WHEN
    final NullPointerException exception =
        Assertions.assertThrows(
            NullPointerException.class,
            () -> builder.protocols((HttpProtocolBuilderWrapper[]) null));

    // THEN
    Assertions.assertEquals(exception.getMessage(), "httpProtocolBuilderWrapper");
  }

  @Test
  public void nullAssertion() {
    // GIVEN
    final JavaSimulation.Builder builder = JavaSimulation.builder();

    // WHEN
    final NullPointerException exception =
        Assertions.assertThrows(
            NullPointerException.class, () -> builder.assertions((Assertion[]) null));

    // THEN
    Assertions.assertEquals(exception.getMessage(), "assertions");
  }

  @Test
  public void nullValue() {
    // GIVEN
    final JavaSimulation.Builder builder = JavaSimulation.builder();

    // WHEN
    builder.scenario().protocols().assertions();
  }
}
