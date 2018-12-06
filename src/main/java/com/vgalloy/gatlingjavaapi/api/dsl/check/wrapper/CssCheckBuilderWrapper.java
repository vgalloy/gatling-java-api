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
import scala.Some;
import scala.collection.Seq;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class CssCheckBuilderWrapper implements Supplier<CssCheckBuilder<String>> {

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

    public CheckBuilderWrapper<CssCheckType, NodeSelector, String> saveAs(String name) {
        Objects.requireNonNull(name);

        ValidatorCheckBuilder<CssCheckType, NodeSelector, String> validatorCheckBuilder = cssCheckBuilder.find();
        CheckBuilder<CssCheckType, NodeSelector, String> checkBuilder = Predef.validatorCheckBuilder2CheckBuilder(validatorCheckBuilder);
        CheckBuilder<CssCheckType, NodeSelector, String> copy = checkBuilder.copy(checkBuilder.extractor(), checkBuilder.validator(), checkBuilder.displayActualValue(), checkBuilder.customName(), new Some(name));
        return new CheckBuilderWrapper<CssCheckType, NodeSelector, String>(copy) {
            @Override
            public CheckMaterializer getCheckMaterializer() {
                return io.gatling.http.Predef.httpBodyCssCheckMaterializer(cssCheckBuilder.selectors());
            }
        };
    }
}
