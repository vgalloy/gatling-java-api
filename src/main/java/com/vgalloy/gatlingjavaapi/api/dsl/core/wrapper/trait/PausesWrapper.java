package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.gatling.core.structure.StructureBuilder;
import scala.concurrent.duration.Duration;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface PausesWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends ExecsWrapper<STRUCTURE, WRAPPER> {

    @SuppressWarnings("unchecked")
    default WRAPPER pause(long length, TimeUnit unit) {
        Objects.requireNonNull(unit);

        return newInstance((STRUCTURE) get().pause(Duration.apply(length, unit)));
    }
}
