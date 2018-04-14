package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import java.util.Objects;
import java.util.function.Supplier;

import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.ValidatorCheckBuilder;
import io.gatling.core.check.extractor.Extractor;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.check.body.HttpBodyCssCheckBuilder;
import io.gatling.http.response.Response;
import jodd.lagarto.dom.NodeSelector;
import scala.Some;
import scala.collection.Seq;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class HttpBodyCssCheckBuilderWrapper implements Supplier<HttpBodyCssCheckBuilder> {

    private final HttpBodyCssCheckBuilder<String> httpBodyCssCheckBuilder;

    public HttpBodyCssCheckBuilderWrapper(HttpBodyCssCheckBuilder<String> httpBodyCssCheckBuilder) {
        this.httpBodyCssCheckBuilder = Objects.requireNonNull(httpBodyCssCheckBuilder);
    }

    @Override
    public HttpBodyCssCheckBuilder get() {
        return httpBodyCssCheckBuilder;
    }

    public Expression<Extractor<NodeSelector, String>> findExtractor(int occurrence) {
        return (Expression<Extractor<NodeSelector, String>>) httpBodyCssCheckBuilder.findExtractor(occurrence);
    }

    public Expression<Extractor<NodeSelector, Seq<String>>> findAllExtractor() {
        return (Expression<Extractor<NodeSelector, Seq<String>>>) httpBodyCssCheckBuilder.findAllExtractor();
    }

    public Expression<Extractor<NodeSelector, Object>> countExtractor() {
        return (Expression<Extractor<NodeSelector, Object>>) httpBodyCssCheckBuilder.countExtractor();
    }

    public CheckBuilder<HttpCheck, Response, NodeSelector, String> saveAs(String name) {
        Objects.requireNonNull(name);

        ValidatorCheckBuilder validatorCheckBuilder = httpBodyCssCheckBuilder.find();
        CheckBuilder<HttpCheck, Response, NodeSelector, String> checkBuilder = validatorCheckBuilder.validate(Expression.of(name));
        return checkBuilder.copy(checkBuilder.validatorCheckBuilder(), checkBuilder.validator(), new Some(name));
    }
}
