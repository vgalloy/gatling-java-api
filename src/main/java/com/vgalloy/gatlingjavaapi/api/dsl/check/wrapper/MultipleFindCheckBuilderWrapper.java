package com.vgalloy.gatlingjavaapi.api.dsl.check.wrapper;

import com.vgalloy.gatlingjavaapi.internal.util.expression.Expression;
import io.gatling.core.check.*;
import io.gatling.core.check.extractor.Extractor;
import scala.collection.Seq;


/**
 * Created by Vincent Galloy on 18/09/2018.
 *
 * @author Vincent Galloy
 */
public interface MultipleFindCheckBuilderWrapper<C extends Check<R>, R, P, X, STRUCTURE extends MultipleFindCheckBuilder<C, R, P, X>>
    extends FindCheckBuilderWrapper<C, R, P, X, STRUCTURE> {

    Expression<Extractor<P, String>> findExtractor(int occurrence);

    Expression<Extractor<P, Seq<String>>> findAllExtractor();

    Expression<Extractor<P, Object>> countExtractor();

    default ValidatorCheckBuilderWrapper<C, R, P, X> find() {
        return find(0);
    }

    default ValidatorCheckBuilderWrapper<C, R, P, X> find(int occurrence) {
        return new ValidatorCheckBuilderWrapper<>(get().find(occurrence));
    }

    default ValidatorCheckBuilderWrapper<C, R, P, Seq<X>> findAll() {
        return new ValidatorCheckBuilderWrapper<>(get().findAll());
    }

    default ValidatorCheckBuilderWrapper<C, R, P, Object> count() {
        return new ValidatorCheckBuilderWrapper<>(get().count());
    }
}
