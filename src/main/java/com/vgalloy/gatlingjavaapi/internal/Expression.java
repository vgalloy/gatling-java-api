package com.vgalloy.gatlingjavaapi.internal;

import io.gatling.commons.validation.Validation;
import io.gatling.core.session.Session;
import scala.Function1;

/**
 * Created by Vincent Galloy on 23/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface Expression<TYPE> extends Function1<Session, Validation<TYPE>> {

}
