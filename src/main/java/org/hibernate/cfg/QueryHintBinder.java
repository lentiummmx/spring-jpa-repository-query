package com.cbs.api.postgresql.cfg;

import com.cbs.api.postgresql.jpa.QueryHints;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import org.hibernate.cfg.annotations.QueryHintDefinition;

public class QueryHintBinder {

    public static <E> Set<String> getQueryHints(
            @Nonnull Class<E> entityClass,
            @Nonnull String queryName) {
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

    private static Stream<String> extractCustomQueryHints(@Nonnull String queryName, QueryHint[] queryHints) {
        QueryHintDefinition hints = new QueryHintDefinition(queryHints);
        // QueryHints.getDefinedHints().forEach(hintName -> hints.getString(queryName, hintName));
        // Stream<String> customQueryHints = QueryHints.getDefinedHints().stream().map(hintName ->
        // hints.getString(queryName, hintName));
        // return customQueryHints;
        return QueryHints.getDefinedHints().stream().map(hintName -> hints.getString(queryName, hintName));

    }
}
