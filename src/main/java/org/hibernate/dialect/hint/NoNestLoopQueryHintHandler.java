package org.hibernate.dialect.hint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoNestLoopQueryHintHandler implements QueryHintHandler {

    public static final NoNestLoopQueryHintHandler INSTANCE = new NoNestLoopQueryHintHandler();

    private static final Pattern QUERY_PATTERN = Pattern.compile("^(select.*?from.*?)(where.*?)$");

    @Override
    public String addQueryHints(String query, String hints) {
        Matcher matcher = QUERY_PATTERN.matcher(query);
        if (matcher.matches() && matcher.groupCount() > 1) {
            String startToken = matcher.group(1);
            String endToken = matcher.group(2);

            return new StringBuilder(" /*+NoNestLoop(")
                    .append(hints)
                    .append(")*/ ")
                    .append(startToken)
                    .append(endToken)
                    .toString();
        } else {
            return query;
        }
    }
}
