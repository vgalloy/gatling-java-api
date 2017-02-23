package com.vgalloy.gatlingjavaapi.internal;

import scala.collection.immutable.$colon$colon;
import scala.collection.immutable.List$;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public class Helper {

	public static Expression<String> buildExpressionFromString(String string) {
		return new ExpressionImpl<>(string);
	}

	public static <T> scala.collection.immutable.List<T> map(T element) {
		return new $colon$colon(element, List$.MODULE$.empty());
	}
}
