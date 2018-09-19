package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.Check;
import io.gatling.core.check.CheckBuilder;
import scala.Some;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface SaveAsWrapper<C extends Check<R>, R, P, X> {

    CheckBuilder<C, R, P, X> toCheckBuilder();

    default CheckBuilder<C, R, P, X> saveAs(String key) {
        final CheckBuilder<C, R, P, X> checkBuilder = toCheckBuilder();
        return checkBuilder.copy(checkBuilder.validatorCheckBuilder(), checkBuilder.validator(), new Some<>(key));
    }
}
