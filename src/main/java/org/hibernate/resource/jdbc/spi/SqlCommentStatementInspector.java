package org.hibernate.resource.jdbc.spi;

import java.util.regex.Pattern;
import org.hibernate.dialect.hint.NoNestLoopQueryHintHandler;

public class SqlCommentStatementInspector implements StatementInspector {

    private static final Pattern SQL_COMMENT_PATTERN = Pattern.compile("\\/\\*.*?\\*\\/\\s*");

    @Override
    public String inspect(String sql) {
        System.out.printf("BEFORE - Executing SQL query: %1$s\n", sql);

        if (sql.contains("DeviceAppVersion.deviceFamilies")) {
            String hints =
                    "api_db_domestic_local.api_device_app_version_device_models api_db_domestic_local.api_device_app_version api_db_domestic_local.api_device_app";
            sql = NoNestLoopQueryHintHandler.INSTANCE.addQueryHints(
                    SQL_COMMENT_PATTERN.matcher(sql).replaceAll(""), hints);
        }
        System.out.printf("AFTER - Executing SQL query: %1$s\n", sql);

        return sql;
    }
}
