package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.http.request.builder.RequestBuilder;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public abstract class RequestBuilderWrapper<STRUCTURE extends RequestBuilder<STRUCTURE>, WRAPPER extends RequestBuilderWrapper<STRUCTURE, WRAPPER>>
        implements Supplier<STRUCTURE> {

    /**
     * Build a new instance of the wrapper
     *
     * @param newStructure the wrapped gatling structure
     * @return the new wrapper
     */
    protected abstract WRAPPER newInstance(STRUCTURE newStructure);

    public WRAPPER header(String name, String value) {
        return newInstance(get().header(name, Expression.of(value)));
    }

    public WRAPPER headers(Map<String, String> headers) {
        final scala.collection.immutable.Map<String, String> map = ScalaHelper.map(headers);
        return newInstance(get().headers(map));
    }

    public WRAPPER queryParam(String name, Object value) {
        return newInstance(get().queryParam(Expression.of(name), Expression.of(value)));
    }
}
