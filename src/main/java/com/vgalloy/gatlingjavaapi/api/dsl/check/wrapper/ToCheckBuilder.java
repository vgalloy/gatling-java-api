package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import io.gatling.core.check.CheckMaterializer;
import io.gatling.http.check.HttpCheck;
import io.gatling.http.response.Response;

public interface ToCheckBuilder<T, P, X> {

  CheckBuilderWrapper<T, P, X> toCheckBuilder();

  CheckMaterializer<T, HttpCheck, Response, P> getMaterializer();
}
