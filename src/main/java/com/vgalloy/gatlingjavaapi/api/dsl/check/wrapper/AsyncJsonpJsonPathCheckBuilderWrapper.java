package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.extractor.Extractor;
import io.gatling.http.check.async.AsyncCheck;
import io.gatling.http.check.async.AsyncJsonpJsonPathCheckBuilder;
import scala.collection.Seq;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class AsyncJsonpJsonPathCheckBuilderWrapper implements MultipleFindCheckBuilderWrapper<AsyncCheck, String, Object, String, AsyncJsonpJsonPathCheckBuilder<String>> {

    private final AsyncJsonpJsonPathCheckBuilder<String> asyncJsonpJsonPathCheckBuilder;

    public AsyncJsonpJsonPathCheckBuilderWrapper(AsyncJsonpJsonPathCheckBuilder<String> asyncJsonpJsonPathCheckBuilder) {
        this.asyncJsonpJsonPathCheckBuilder = Objects.requireNonNull(asyncJsonpJsonPathCheckBuilder);
    }

    @Override
    public AsyncJsonpJsonPathCheckBuilder<String> get() {
        return asyncJsonpJsonPathCheckBuilder;
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<Object, String>> findExtractor(int occurrence) {
        return (Expression<Extractor<Object, String>>) asyncJsonpJsonPathCheckBuilder.findExtractor(occurrence);
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<Object, Seq<String>>> findAllExtractor() {
        return (Expression<Extractor<Object, Seq<String>>>) asyncJsonpJsonPathCheckBuilder.findAllExtractor();
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<Object, Object>> countExtractor() {
        return (Expression<Extractor<Object, Object>>) asyncJsonpJsonPathCheckBuilder.countExtractor();
    }
}
