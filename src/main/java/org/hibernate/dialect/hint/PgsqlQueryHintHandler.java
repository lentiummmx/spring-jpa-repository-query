package org.hibernate.dialect.hint;

import ch.qos.logback.classic.db.names.TableName;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public class PgsqlQueryHintHandler implements QueryHintHandler {

    public static final PgsqlQueryHintHandler INSTANCE = new PgsqlQueryHintHandler();

    private static final Pattern QUERY_PATTERN =
            Pattern.compile("^(select .*? from .*?)(.*?\\.{0,}.*?)( where.*?)$", Pattern.CASE_INSENSITIVE);

    /**
     * @param query original query
     * @param hints hints to be applied
     * @return
     */
    @Override
    public String addQueryHints(String query, String hints) {
        Matcher matcher = QUERY_PATTERN.matcher(query);
        if (matcher.matches() && matcher.groupCount() > 1) {
            String startToken = matcher.group(1);
            System.out.println(String.format("startToken :: %s", startToken));
            String tableToken = matcher.group(2);
            System.out.println(String.format("tableToken :: %s", tableToken));
            TableName tableName = parseTableToken(tableToken);
            System.out.println(String.format("tableName :: %s", tableName));
            System.out.println(String.format("tableAlias :: %s", tableName.getTableAlias()));
            String endToken = matcher.group(3);
            System.out.println(String.format("endToken :: %s", endToken));

            /*
             * startToken :: select useraccoun0_.userId as userId1_86_,
             * useraccoun0_.accountStatus as accountS2_86_, useraccoun0_.address_city as
             * address_3_86_, useraccoun0_.address_country as address_4_86_,
             * useraccoun0_.address_state as address_5_86_, useraccoun0_.address_street as
             * address_6_86_, useraccoun0_.address_zipcode as address_7_86_,
             * useraccoun0_.birthday_day as birthday8_86_, useraccoun0_.birthday_month as
             * birthday9_86_, useraccoun0_.birthday_year as birthda10_86_,
             * useraccoun0_.createdDate as created11_86_, useraccoun0_.email as
             * email12_86_, useraccoun0_.firstName as firstNa13_86_, useraccoun0_.gender
             * as gender14_86_, useraccoun0_.homePhoneNumber as homePho15_86_,
             * useraccoun0_.lastName as lastNam16_86_, useraccoun0_.lastPingDate as
             * lastPin17_86_, useraccoun0_.ne as ne18_86_, useraccoun0_.nf as nf19_86_,
             * useraccoun0_.nl as nl20_86_, useraccoun0_.encryption_level as
             * encrypt21_86_, useraccoun0_.password as passwor22_86_,
             * useraccoun0_.primaryPhotoPath as primary23_86_,
             * useraccoun0_.region_override as region_24_86_, useraccoun0_.role as
             * role25_86_, useraccoun0_.sha512EmailHash as sha26_86_, useraccoun0_.siteId
             * as siteId27_86_, useraccoun0_.updatedDate as updated28_86_,
             * useraccoun0_.username as usernam29_86_ from tableToken ::
             * api_db_domestic_local.user useraccoun0_ tableAlias :: useraccoun0_ endToken
             * :: where useraccoun0_.email=? limit ? Hibernate: / *+ IndexScan(user
             * idx_userid_e1b idx_userid_c1b) * / select useraccoun0_.userId as
             * userId1_86_, useraccoun0_.accountStatus as accountS2_86_,
             * useraccoun0_.address_city as address_3_86_, useraccoun0_.address_country as
             * address_4_86_, useraccoun0_.address_state as address_5_86_,
             * useraccoun0_.address_street as address_6_86_, useraccoun0_.address_zipcode
             * as address_7_86_, useraccoun0_.birthday_day as birthday8_86_,
             * useraccoun0_.birthday_month as birthday9_86_, useraccoun0_.birthday_year as
             * birthda10_86_, useraccoun0_.createdDate as created11_86_,
             * useraccoun0_.email as email12_86_, useraccoun0_.firstName as firstNa13_86_,
             * useraccoun0_.gender as gender14_86_, useraccoun0_.homePhoneNumber as
             * homePho15_86_, useraccoun0_.lastName as lastNam16_86_,
             * useraccoun0_.lastPingDate as lastPin17_86_, useraccoun0_.ne as ne18_86_,
             * useraccoun0_.nf as nf19_86_, useraccoun0_.nl as nl20_86_,
             * useraccoun0_.encryption_level as encrypt21_86_, useraccoun0_.password as
             * passwor22_86_, useraccoun0_.primaryPhotoPath as primary23_86_,
             * useraccoun0_.region_override as region_24_86_, useraccoun0_.role as
             * role25_86_, useraccoun0_.sha512EmailHash as sha26_86_, useraccoun0_.siteId
             * as siteId27_86_, useraccoun0_.updatedDate as updated28_86_,
             * useraccoun0_.username as usernam29_86_ from api_db_domestic_local.user
             * useraccoun0_ where useraccoun0_.email=? limit ?
             */

            return new StringBuilder("/*+ ")
                    .append(replaceTableNameWithAlias(hints, tableName.getTableName(), tableName.getTableAlias()))
                    .append(" */ ")
                    .append(query)
                    .toString();
        } else {
            return query;
        }
    }

    private String replaceTableNameWithAlias(String hints, String tableName, String tableAlias) {
        return hints.replaceFirst(tableName, tableAlias);
    }

    public TableName parseTableToken(String tableToken) {
        TableName tableName = null;
        String[] tableAlias = tableToken.split(" ");
        if (tableAlias.length == 1) {
            tableName = new TableName(tableToken, null);
        } else if (tableAlias.length == 2) {
            tableName = new TableName(tableAlias[0], tableAlias[1]);
        } else if (tableAlias.length == 3 && tableAlias[1].equalsIgnoreCase("as")) {
            tableName = new TableName(tableAlias[0], tableAlias[2]);
        }
        return tableName;
    }

    @Getter
    @ToString
    @EqualsAndHashCode
    private class TableName {

        private String tableSchema;

        private String tableName;

        private String tableAlias;

        public TableName(String tableName, String tableAlias) {
            this.tableName = tableName;
            this.tableAlias = tableAlias;
            this.tableSchema = getTableSchema(tableName);
        }

        private String getTableSchema(String tableName) {
            String[] schemaTable = tableName.split("\\.");
            if (schemaTable.length > 1) {
                this.tableName = schemaTable[1];
                return schemaTable[0];
            }
            return "";
        }
    }
}
