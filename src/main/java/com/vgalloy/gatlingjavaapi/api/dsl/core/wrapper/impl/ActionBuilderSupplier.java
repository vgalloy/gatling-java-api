package com.vgalloy.gatlingjavaapi.api.dsl.core.wrapper.impl;

import io.gatling.core.action.builder.ActionBuilder;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface ActionBuilderSupplier {

  ActionBuilder toActionBuilder();
}
