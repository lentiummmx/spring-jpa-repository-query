package org.hibernate.dialect;

import java.util.List;
import org.hibernate.dialect.hint.PgsqlQueryHintHandler;

public class YugabyteDialect extends PostgreSQL82Dialect {

    @Override
    public String getQueryHintString(String query, List<String> hintList) {
        // return super.getQueryHintString(query, hintList);
        final String hints = String.join(" ", hintList);

        if (hints.isEmpty()) {
            return query;
        }

        return getQueryHintString(query, hints);
    }

    @Override
    public String getQueryHintString(String query, String hints) {
        // return super.getQueryHintString(query, hints);
        // return new StringBuilder("/*+ ")
        // .append(hints)
        // .append(" */ ")
        // .append(query)
        // .toString();
        return PgsqlQueryHintHandler.INSTANCE.addQueryHints(query, hints);
    }
}
