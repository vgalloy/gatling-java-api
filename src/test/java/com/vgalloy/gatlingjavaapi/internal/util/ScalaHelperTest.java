package com.vgalloy.gatlingjavaapi.internal.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ScalaHelperTest {

  @Test
  public void orderArray() {
    // GIVEN
    Integer[] array = new Integer[] {1, 2, 3};

    // WHEN
    scala.collection.immutable.List<Integer> result = ScalaHelper.map(array);

    // THEN
    Assert.assertEquals(Integer.valueOf(1), result.apply(0));
  }

  @Test
  public void orderList() {
    // GIVEN
    List<Integer> list = Arrays.asList(1, 2, 3);

    // WHEN
    scala.collection.immutable.List<Integer> result =
        list.stream().collect(ScalaHelper.toScalaList());

    // THEN
    Assert.assertEquals(Integer.valueOf(1), result.apply(0));
  }

  @Test
  public void mapConversion() {
    // GIVEN
    Map<Integer, String> map = new HashMap<>();
    map.put(1, "one");
    map.put(2, "two");
    map.put(3, "three");

    // WHEN
    scala.collection.immutable.Map<Integer, String> result = ScalaHelper.map(map);

    // THEN
    Assert.assertEquals("one", result.apply(1));
    Assert.assertEquals("two", result.apply(2));
    Assert.assertEquals("three", result.apply(3));
  }
}
