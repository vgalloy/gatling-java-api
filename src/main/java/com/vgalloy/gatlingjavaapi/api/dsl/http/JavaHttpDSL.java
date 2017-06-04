package com.vgalloy.gatlingjavaapi.api.dsl.http;

import com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.HttpWrapper;
import com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper.HttpProtocolBuilderWrapper;
import com.vgalloy.gatlingjavaapi.internal.GatlingConfigurationSupplier;
import com.vgalloy.gatlingjavaapi.internal.util.Expression;
import com.vgalloy.gatlingjavaapi.internal.util.ScalaHelper;
import io.gatling.core.check.ValidatorCheckBuilder;
import io.gatling.http.Predef;
import io.gatling.http.check.status.HttpStatusCheckBuilder;
import io.gatling.http.request.builder.Http;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class JavaHttpDSL {

    public static final ValidatorCheckBuilder status = HttpStatusCheckBuilder.Status().find();

    /**
     * Constructor.
     * To prevent external instantiation
     */
    private JavaHttpDSL() {
        throw new AssertionError();
    }

    public static HttpWrapper http(String requestName) {
        Expression<String> requestExpression = ScalaHelper.toExpression(requestName);
        return new HttpWrapper(new Http(requestExpression));
    }

    public static HttpProtocolBuilderWrapper http() {
        return new HttpProtocolBuilderWrapper(Predef.http(GatlingConfigurationSupplier.GATLING_CONFIGURATION));
    }
}
