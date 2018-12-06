package com.vgalloy.gatlingjavaapi.api.dsl.check;

import java.util.Objects;

import io.gatling.core.Predef;
import io.gatling.core.check.extractor.css.CssCheckBuilder;
import io.gatling.core.check.extractor.css.CssSelectors;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.CssCheckBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public final class JavaCheckSupport {

    /**
     * Constructor.
     * To prevent instantiation
     */
    private JavaCheckSupport() {
        throw new AssertionError("No instance of JavaCheckSupport");
    }

    public static CssCheckBuilderWrapper css(String selector, String nodeAttribute) {
        Objects.requireNonNull(selector);
        Objects.requireNonNull(nodeAttribute);

        CssSelectors cssExtractor = io.gatling.core.Predef.defaultCssSelectors();
        CssCheckBuilder<String> cssCheckBuilder = Predef.css(Expression.of(selector), nodeAttribute, cssExtractor);
        return new CssCheckBuilderWrapper(cssCheckBuilder);
    }
}
