package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.http.check.async.AsyncCheck;
import io.gatling.http.check.async.AsyncPlainCheckBuilder;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 14/04/18.
 *
 * @author Vincent Galloy
 */
public class AsyncPlainCheckBuilderWrapper implements FindCheckBuilderWrapper<AsyncCheck, String, String, String, AsyncPlainCheckBuilder> {

    private final AsyncPlainCheckBuilder asyncPlainCheckBuilder;

    public AsyncPlainCheckBuilderWrapper(AsyncPlainCheckBuilder asyncPlainCheckBuilder) {
        this.asyncPlainCheckBuilder = Objects.requireNonNull(asyncPlainCheckBuilder);
    }

    @Override
    public AsyncPlainCheckBuilder get() {
        return asyncPlainCheckBuilder;
    }
}
