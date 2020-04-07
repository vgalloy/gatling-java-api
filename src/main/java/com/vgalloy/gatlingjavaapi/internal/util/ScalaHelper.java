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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import scala.Tuple2;
import scala.collection.immutable.List$;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ScalaHelper {

  /** Constructor. To prevent instantiation */
  private ScalaHelper() {
    throw new AssertionError("No instance of ScalaHelper");
  }

  @SafeVarargs
  public static <T> scala.collection.immutable.List<T> map(T... elementList) {
    Objects.requireNonNull(elementList);
    return Arrays.stream(elementList).collect(toScalaList());
  }

  public static <T> List<T> map(scala.collection.immutable.List<T> list) {
    return new ArrayList<>(
        scala.collection.JavaConverters.asJavaCollectionConverter(list).asJavaCollection());
  }

  public static <A, B> scala.collection.immutable.Map<A, B> map(Map<A, B> map) {
    scala.collection.immutable.Map<A, B> result =
        new scala.collection.immutable.HashMap<A, B>().empty();
    for (Map.Entry<A, B> entry : map.entrySet()) {
      Tuple2<A, B> newElement = new Tuple2<>(entry.getKey(), entry.getValue());
      result = result.$plus(newElement);
    }
    return result;
  }

  public static <T> Collector<T, List<T>, scala.collection.immutable.List<T>> toScalaList() {
    return Collector.of(
        ArrayList::new,
        List::add,
        (left, right) -> {
          left.addAll(right);
          return left;
        },
        (list) -> {
          Collections.reverse(list);
          return list.stream()
              .reduce(
                  List$.MODULE$.empty(),
                  scala.collection.immutable.List::$colon$colon,
                  scala.collection.immutable.List::$colon$colon$colon);
        });
  }
}
