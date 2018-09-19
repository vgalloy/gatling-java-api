package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.extractor.Extractor;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.check.body.HttpBodyCssCheckBuilder;
import io.gatling.http.response.Response;
import jodd.lagarto.dom.NodeSelector;
import scala.collection.Seq;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class HttpBodyCssCheckBuilderWrapper implements MultipleFindCheckBuilderWrapper<HttpCheck, Response, NodeSelector, String, HttpBodyCssCheckBuilder<String>> {

    private final HttpBodyCssCheckBuilder<String> httpBodyCssCheckBuilder;

    public HttpBodyCssCheckBuilderWrapper(HttpBodyCssCheckBuilder<String> httpBodyCssCheckBuilder) {
        this.httpBodyCssCheckBuilder = Objects.requireNonNull(httpBodyCssCheckBuilder);
    }

    @Override
    public HttpBodyCssCheckBuilder<String> get() {
        return httpBodyCssCheckBuilder;
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<NodeSelector, String>> findExtractor(int occurrence) {
        return (Expression<Extractor<NodeSelector, String>>) httpBodyCssCheckBuilder.findExtractor(occurrence);
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<NodeSelector, Seq<String>>> findAllExtractor() {
        return (Expression<Extractor<NodeSelector, Seq<String>>>) httpBodyCssCheckBuilder.findAllExtractor();
    }

    @SuppressWarnings("unchecked")
    public Expression<Extractor<NodeSelector, Object>> countExtractor() {
        return (Expression<Extractor<NodeSelector, Object>>) httpBodyCssCheckBuilder.countExtractor();
    }
}
