package com.vgalloy.gatlingjavaapi.api.dsl.http.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.http.action.ws.Ws;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Created by Vincent Galloy on 24/02/2017.
 *
 * @author Vincent Galloy.
 */
public final class WsWrapper implements Supplier<Ws> {

  private final Ws ws;

  public WsWrapper(Ws ws) {
    this.ws = Objects.requireNonNull(ws);
  }

  @Override
  public Ws get() {
    return ws;
  }

  public WsWrapper wsName(String wsName) {
    return new WsWrapper(ws.wsName(wsName));
  }

  public WsConnectRequestBuilderSupplier connect(String url) {
    return new WsConnectRequestBuilderSupplier(ws.connect(Expression.of(url)));
  }

  public WsSendBinaryFrameBuilderWrapper sendBytes(byte[] bytes) {
    return new WsSendBinaryFrameBuilderWrapper(ws.sendBytes(Expression.of(bytes)));
  }

  public WsSendTextFrameBuilderWrapper sendText(String text) {
    return new WsSendTextFrameBuilderWrapper(ws.sendText(Expression.of(text)));
  }

  public WsCloseBuilderWrapper close() {
    return new WsCloseBuilderWrapper(ws.close());
  }
}
