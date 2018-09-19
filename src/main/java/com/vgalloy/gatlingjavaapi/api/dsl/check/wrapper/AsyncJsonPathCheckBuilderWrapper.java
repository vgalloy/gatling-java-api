package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.extractor.Extractor;
import io.gatling.http.check.async.AsyncCheck;
import io.gatling.http.check.async.AsyncJsonPathCheckBuilder;
import scala.collection.Seq;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class AsyncJsonPathCheckBuilderWrapper implements MultipleFindCheckBuilderWrapper<AsyncCheck, String, Object, String, AsyncJsonPathCheckBuilder<String>> {

    private final AsyncJsonPathCheckBuilder<String> asyncJsonPathCheckBuilder;

    public AsyncJsonPathCheckBuilderWrapper(AsyncJsonPathCheckBuilder<String> asyncJsonPathCheckBuilder) {
        this.asyncJsonPathCheckBuilder = Objects.requireNonNull(asyncJsonPathCheckBuilder);
    }

    @Override
    public AsyncJsonPathCheckBuilder<String> get() {
        return asyncJsonPathCheckBuilder;
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<Object, String>> findExtractor(int occurrence) {
        return (Expression<Extractor<Object, String>>) asyncJsonPathCheckBuilder.findExtractor(occurrence);
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<Object, Seq<String>>> findAllExtractor() {
        return (Expression<Extractor<Object, Seq<String>>>) asyncJsonPathCheckBuilder.findAllExtractor();
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<Object, Object>> countExtractor() {
        return (Expression<Extractor<Object, Object>>) asyncJsonPathCheckBuilder.countExtractor();
    }
}
