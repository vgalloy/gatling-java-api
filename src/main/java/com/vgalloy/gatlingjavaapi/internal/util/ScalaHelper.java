package com.vgalloy.gatlingjavaapi.internal.util;

import scala.Tuple2;
import scala.collection.immutable.List$;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class ScalaHelper {

	/**
	 * Constructor.
	 * To prevent external instantiation
	 */
	private ScalaHelper() {
		throw new AssertionError();
	}

	public static <T> Expression<T> toExpression(T item) {
		return new ExpressionImpl<>(item);
	}

	public static <T> scala.collection.immutable.List<T> map(T... elementList) {
		Objects.requireNonNull(elementList);
		return Arrays.stream(elementList)
				.collect(toScalaList());
	}

	public static <T> List<T> map(scala.collection.immutable.List<T> list) {
		return new ArrayList<>(scala.collection.JavaConverters.asJavaCollectionConverter(list).asJavaCollection());
	}

	public static <A, B> scala.collection.immutable.Map<A, B> map(Map<A, B> map) {
		scala.collection.immutable.Map<A, B> result = new scala.collection.immutable.HashMap<A, B>().empty();
		for (Map.Entry<A, B> entry : map.entrySet()) {
			Tuple2<A, B> newElement = new Tuple2<>(entry.getKey(), entry.getValue());
			result.$plus(newElement);
		}
		return result;
	}

	public static <T> Collector<T, List<T>, scala.collection.immutable.List<T>> toScalaList() {
		return new Collector<T, List<T>, scala.collection.immutable.List<T>>() {
			@Override
			public Supplier<List<T>> supplier() {
				return ArrayList::new;
			}

			@Override
			public BiConsumer<List<T>, T> accumulator() {
				return List::add;
			}

			@Override
			public BinaryOperator<List<T>> combiner() {
				return (left, right) -> {
					left.addAll(right);
					return left;
				};
			}

			@Override
			public Function<List<T>, scala.collection.immutable.List<T>> finisher() {
				return (list) -> {
					Collections.reverse(list);
					return list.stream().reduce(
							List$.MODULE$.empty(),
							scala.collection.immutable.List::$colon$colon,
							scala.collection.immutable.List::$colon$colon$colon);
				};
			}

			@Override
			public Set<Characteristics> characteristics() {
				return new HashSet<>();
			}
		};
	}
}
