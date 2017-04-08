package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.gatling.core.structure.StructureBuilder;
import scala.concurrent.duration.Duration;

/**
 * @author Vincent Galloy.
 *         Created by Vincent Galloy on 28/02/2017.
 */
public interface PausesWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends ExecsWrapper<STRUCTURE, WRAPPER> {

    default WRAPPER pause(long length, TimeUnit unit) {
        Objects.requireNonNull(unit);

        return newInstance((STRUCTURE) get().pause(Duration.apply(length, unit)));
    }
}
