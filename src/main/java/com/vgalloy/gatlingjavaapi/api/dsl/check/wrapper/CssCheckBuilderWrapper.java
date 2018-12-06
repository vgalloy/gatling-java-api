package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import java.util.Objects;
import java.util.function.Supplier;

import io.gatling.core.Predef;
import io.gatling.core.check.CheckBuilder;
import io.gatling.core.check.CheckMaterializer;
import io.gatling.core.check.ValidatorCheckBuilder;
import io.gatling.core.check.extractor.Extractor;
import io.gatling.core.check.extractor.css.CssCheckBuilder;
import io.gatling.core.check.extractor.css.CssCheckType;
import jodd.lagarto.dom.NodeSelector;
import scala.collection.Seq;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class CssCheckBuilderWrapper implements Supplier<CssCheckBuilder<String>>, SaveAsWrapper<CssCheckType, NodeSelector, String> {

    private final CssCheckBuilder<String> cssCheckBuilder;

    public CssCheckBuilderWrapper(CssCheckBuilder<String> cssCheckBuilder) {
        this.cssCheckBuilder = Objects.requireNonNull(cssCheckBuilder);
    }

    @Override
    public CssCheckBuilder<String> get() {
        return cssCheckBuilder;
    }

    public Expression<Extractor<NodeSelector, String>> findExtractor(int occurrence) {
        return (Expression<Extractor<NodeSelector, String>>) cssCheckBuilder.findExtractor(occurrence);
    }

    public Expression<Extractor<NodeSelector, Seq<String>>> findAllExtractor() {
        return (Expression<Extractor<NodeSelector, Seq<String>>>) cssCheckBuilder.findAllExtractor();
    }

    public Expression<Extractor<NodeSelector, Object>> countExtractor() {
        return (Expression<Extractor<NodeSelector, Object>>) cssCheckBuilder.countExtractor();
    }

    @Override
    public CheckBuilder<CssCheckType, NodeSelector, String> toCheckBuilder() {
        ValidatorCheckBuilder<CssCheckType, NodeSelector, String> validatorCheckBuilder = cssCheckBuilder.find();
        return Predef.validatorCheckBuilder2CheckBuilder(validatorCheckBuilder);
    }

    @Override
    public CheckMaterializer checkMaterializer() {
        return io.gatling.http.Predef.httpBodyCssCheckMaterializer(cssCheckBuilder.selectors());
    }
}
