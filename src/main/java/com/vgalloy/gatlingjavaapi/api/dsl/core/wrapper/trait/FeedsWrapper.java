package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.trait;

import java.util.Objects;

import io.gatling.core.structure.StructureBuilder;

import com.vgalloy.gatlingjavaapi.api.dsl.feeder.wrapper.RecordSeqFeederBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 28/02/2017.
 *
 * @author Vincent Galloy.
 */
public interface FeedsWrapper<STRUCTURE extends StructureBuilder, WRAPPER extends StructureBuilderWrapper<STRUCTURE, WRAPPER>> extends ExecsWrapper<STRUCTURE, WRAPPER> {

    @SuppressWarnings("unchecked")
    default WRAPPER feed(RecordSeqFeederBuilderWrapper recordSeqFeederBuilderWrapper) {
        return feed(recordSeqFeederBuilderWrapper, 1);
    }

    @SuppressWarnings("unchecked")
    default WRAPPER feed(RecordSeqFeederBuilderWrapper recordSeqFeederBuilderWrapper, int number) {
        Objects.requireNonNull(recordSeqFeederBuilderWrapper);

        return newInstance((STRUCTURE) get().feed(recordSeqFeederBuilderWrapper.get(), Expression.of(number)));
    }
}
