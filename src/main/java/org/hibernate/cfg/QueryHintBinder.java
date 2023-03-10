package org.hibernate.cfg;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import lombok.NonNull;
import org.hibernate.cfg.annotations.QueryHintDefinition;
import org.hibernate.jpa.PgsqlQueryHints;

public class QueryHintBinder {

    public static <E> Set<String> getQueryHints(@NonNull Class<E> entityClass, @NonNull String queryName) {
        Stream<String> customQueryHints = Stream.empty();

        NamedQueries namedQueriesAnn = entityClass.getAnnotation(NamedQueries.class);
        if (namedQueriesAnn != null) {
            NamedQuery[] namedQueries = namedQueriesAnn.value();
            for (NamedQuery namedQuery : namedQueries) {
                if (namedQuery.name().equals(queryName)) {
                    QueryHint[] queryHints = namedQuery.hints();
                    customQueryHints = Stream.concat(customQueryHints, extractCustomQueryHints(queryName, queryHints));
                    break;
                }
            }
        }

        NamedNativeQueries namedNativeQueriesAnn = entityClass.getAnnotation(NamedNativeQueries.class);
        if (namedNativeQueriesAnn != null) {
            NamedNativeQuery[] namedNativeQueries = namedNativeQueriesAnn.value();
            for (NamedNativeQuery namedNativeQuery : namedNativeQueries) {
                if (namedNativeQuery.name().equals(queryName)) {
                    QueryHint[] queryHints = namedNativeQuery.hints();
                    customQueryHints = Stream.concat(customQueryHints, extractCustomQueryHints(queryName, queryHints));
                    break;
                }
            }
        }

        return customQueryHints.filter(Objects::nonNull).collect(Collectors.toUnmodifiableSet());
    }

    private static Stream<String> extractCustomQueryHints(@NonNull String queryName, QueryHint[] queryHints) {
        QueryHintDefinition hints = new QueryHintDefinition(queryHints);
        // PgsqlQueryHints.getDefinedHints().forEach(hintName ->
        // hints.getString(queryName, hintName));
        // Stream<String> customQueryHints =
        // PgsqlQueryHints.getDefinedHints().stream().map(hintName ->
        // hints.getString(queryName, hintName));
        // return customQueryHints;
        return PgsqlQueryHints.getDefinedHints().stream().map(hintName -> hints.getString(queryName, hintName));
    }
}
