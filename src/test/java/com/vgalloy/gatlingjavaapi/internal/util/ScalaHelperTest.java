package com.vgalloy.gatlingjavaapi.internal.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Vincent Galloy.
 *         Created by Vincent Galloy on 28/02/2017.
 */
public class ScalaHelperTest {

	@Test
	public void orderArray() {
		// GIVEN
		Integer[] array = new Integer[]{1, 2, 3};

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
		scala.collection.immutable.List<Integer> result = list.stream().collect(ScalaHelper.toScalaList());

		// THEN
		Assert.assertEquals(Integer.valueOf(1), result.apply(0));
	}
}