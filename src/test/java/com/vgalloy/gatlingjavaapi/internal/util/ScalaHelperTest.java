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
package com.vgalloy.gatlingjavaapi.internal.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
final class ScalaHelperTest {

  @Test
  void orderArray() {
    // GIVEN
    Integer[] array = new Integer[] {1, 2, 3};

    // WHEN
    scala.collection.immutable.List<Integer> result = ScalaHelper.map(array);

    // THEN
    Assertions.assertEquals(Integer.valueOf(1), result.apply(0));
  }

  @Test
  void orderList() {
    // GIVEN
    List<Integer> list = Arrays.asList(1, 2, 3);

    // WHEN
    scala.collection.immutable.List<Integer> result =
        list.stream().collect(ScalaHelper.toScalaList());

    // THEN
    Assertions.assertEquals(Integer.valueOf(1), result.apply(0));
  }

  @Test
  void mapConversion() {
    // GIVEN
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");

    // WHEN
    scala.collection.immutable.Map<Integer, String> result = ScalaHelper.map(map);

    // THEN
    Assertions.assertEquals("one", result.apply(1));
    Assertions.assertEquals("two", result.apply(2));
    Assertions.assertEquals("three", result.apply(3));
  }
}
