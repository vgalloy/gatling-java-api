package com.vgalloy.gatlingjavaapi.api.dsl.check;

import java.util.Objects;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.TimeoutStepWrapper;
import io.gatling.core.check.extractor.css.CssExtractorFactory;
import io.gatling.http.Predef;

import com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper.HttpBodyCssCheckBuilderWrapper;
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
        throw new AssertionError();
    }

    public static HttpBodyCssCheckBuilderWrapper css(String selector, String nodeAttribute) {
        Objects.requireNonNull(selector);
        Objects.requireNonNull(nodeAttribute);

        final CssExtractorFactory cssExtractorFactory = io.gatling.core.Predef.defaultCssExtractorFactory();
        return new HttpBodyCssCheckBuilderWrapper(Predef.css(Expression.of(selector), nodeAttribute, cssExtractorFactory));
    }

    public static TimeoutStepWrapper wsListen() {
        return new TimeoutStepWrapper(Predef.wsListen());
    }

    public static TimeoutStepWrapper wsAwait() {
        return new TimeoutStepWrapper(Predef.wsAwait());
    }
}
