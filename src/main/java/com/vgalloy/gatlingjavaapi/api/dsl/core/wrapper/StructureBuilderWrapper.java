package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.StructureBuilder;
import io.gatling.http.action.sync.HttpRequestActionBuilder;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface StructureBuilderWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends ExecsWrapper<STRUCTURE, WRAPPER>,
    PausesWrapper<STRUCTURE, WRAPPER> {

    @SuppressWarnings("unchecked")
    default WRAPPER exec(HttpRequestBuilderWrapper httpRequestBuilderWrapper) {
        Objects.requireNonNull(httpRequestBuilderWrapper);

        return newInstance((STRUCTURE) get().exec(new HttpRequestActionBuilder(httpRequestBuilderWrapper.get())));
    }

    @SuppressWarnings("unchecked")
    default WRAPPER exec(StructureSupportWrapper... structureSupportWrappers) {
        Objects.requireNonNull(structureSupportWrappers);

        scala.collection.immutable.List<ChainBuilder> list = Arrays.stream(structureSupportWrappers)
            .map(StructureSupportWrapper::get)
            .collect(ScalaHelper.toScalaList());
        return newInstance((STRUCTURE) get().exec(list));
    }
}
