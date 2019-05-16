package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.MultipleFindCheckBuilder;
import io.gatling.core.check.extractor.Extractor;
import scala.collection.Seq;

/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface MultipleFindCheckBuilderWrapper<
        T, P, X, STRUCTURE extends MultipleFindCheckBuilder<T, P, X>>
    extends FindCheckBuilderWrapper<T, P, X, STRUCTURE> {

  Expression<Extractor<P, String>> findExtractor(int occurrence);

  Expression<Extractor<P, Seq<String>>> findAllExtractor();

  Expression<Extractor<P, Object>> countExtractor();

  default ValidatorCheckBuilderWrapper<T, P, X> find() {
    return find(0);
  }

  default ValidatorCheckBuilderWrapper<T, P, X> find(int occurrence) {
    return new ValidatorCheckBuilderWrapper<>(get().find(occurrence));
  }

  default ValidatorCheckBuilderWrapper<T, P, Seq<X>> findAll() {
    return new ValidatorCheckBuilderWrapper<>(get().findAll());
  }

  default ValidatorCheckBuilderWrapper<T, P, Object> count() {
    return new ValidatorCheckBuilderWrapper<>(get().count());
  }
}
