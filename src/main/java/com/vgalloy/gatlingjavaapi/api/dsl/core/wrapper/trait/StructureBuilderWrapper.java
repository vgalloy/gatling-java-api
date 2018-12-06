package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import java.util.Arrays;
import java.util.Objects;

import io.gatling.core.structure.ChainBuilder;
import io.gatling.core.structure.StructureBuilder;
import io.gatling.http.action.HttpRequestActionBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.ChainBuilderWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl.HttpRequestBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface StructureBuilderWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>>
    extends
    ExecsWrapper<STRUCTURE, WRAPPER>,
    PausesWrapper<STRUCTURE, WRAPPER>,
    LoopWrapper<STRUCTURE, WRAPPER>,
    FeedsWrapper<STRUCTURE, WRAPPER>,
    ErrorWrapper<STRUCTURE, WRAPPER> {

    @SuppressWarnings("unchecked")
    default WRAPPER exec(HttpRequestBuilderWrapper httpRequestBuilderWrapper) {
        Objects.requireNonNull(httpRequestBuilderWrapper);

        return newInstance((STRUCTURE) get().exec(new HttpRequestActionBuilder(httpRequestBuilderWrapper.get())));
    }

    @SuppressWarnings("unchecked")
    default WRAPPER exec(ChainBuilderWrapper... structureSupportWrappers) {
        Objects.requireNonNull(structureSupportWrappers);

        scala.collection.immutable.List<ChainBuilder> list = Arrays.stream(structureSupportWrappers)
            .map(ChainBuilderWrapper::get)
            .collect(ScalaHelper.toScalaList());
        return newInstance((STRUCTURE) get().exec(list));
    }
}
