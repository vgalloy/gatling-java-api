package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.extractor.Extractor;
import io.gatling.http.check.async.AsyncCheck;
import io.gatling.http.check.async.AsyncRegexCheckBuilder;
import scala.collection.Seq;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class AsyncRegexCheckBuilderWrapper implements MultipleFindCheckBuilderWrapper<AsyncCheck, String, CharSequence, String, AsyncRegexCheckBuilder<String>> {

    private final AsyncRegexCheckBuilder<String> asyncRegexCheckBuilder;

    public AsyncRegexCheckBuilderWrapper(AsyncRegexCheckBuilder<String> asyncRegexCheckBuilder) {
        this.asyncRegexCheckBuilder = Objects.requireNonNull(asyncRegexCheckBuilder);
    }

    @Override
    public AsyncRegexCheckBuilder<String> get() {
        return asyncRegexCheckBuilder;
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<CharSequence, String>> findExtractor(int occurrence) {
        return (Expression<Extractor<CharSequence, String>>) asyncRegexCheckBuilder.findExtractor(occurrence);
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<CharSequence, Seq<String>>> findAllExtractor() {
        return (Expression<Extractor<CharSequence, Seq<String>>>) asyncRegexCheckBuilder.findAllExtractor();
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<CharSequence, Object>> countExtractor() {
        return (Expression<Extractor<CharSequence, Object>>) asyncRegexCheckBuilder.countExtractor();
    }
}
