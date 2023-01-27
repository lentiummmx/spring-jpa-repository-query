package mx.com.xoco.nuniez.sja;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JvRegExpTest {

    private static final Pattern QUERY_PATTERN =
            Pattern.compile(".*(\\sfrom\\s.*?\\.*\\s.*?\\s)+.*", Pattern.CASE_INSENSITIVE);

    public static void main(String[] args) {
        String query =
                "select useraccoun0_.userId as userId1_86_, useraccoun0_.accountStatus as accountS2_86_, useraccoun0_.address_city as address_3_86_, useraccoun0_.address_country as address_4_86_, useraccoun0_.address_state as address_5_86_, useraccoun0_.address_street as address_6_86_, useraccoun0_.address_zipcode as address_7_86_, useraccoun0_.birthday_day as birthday8_86_, useraccoun0_.birthday_month as birthday9_86_, useraccoun0_.birthday_year as birthda10_86_, useraccoun0_.createdDate as created11_86_, useraccoun0_.email as email12_86_, useraccoun0_.firstName as firstNa13_86_, useraccoun0_.gender as gender14_86_, useraccoun0_.homePhoneNumber as homePho15_86_, useraccoun0_.lastName as lastNam16_86_, useraccoun0_.lastPingDate as lastPin17_86_, useraccoun0_.ne as ne18_86_, useraccoun0_.nf as nf19_86_, useraccoun0_.nl as nl20_86_, useraccoun0_.encryption_level as encrypt21_86_, useraccoun0_.password as passwor22_86_, useraccoun0_.primaryPhotoPath as primary23_86_, useraccoun0_.region_override as region_24_86_, useraccoun0_.role as role25_86_, useraccoun0_.sha512EmailHash as sha26_86_, useraccoun0_.siteId as siteId27_86_, useraccoun0_.updatedDate as updated28_86_, useraccoun0_.username as usernam29_86_ from api_db_domestic_local.user useraccoun0_ where useraccoun0_.email=? limit ?";
        query =
                "select devicefami0_.device_app_version_id as device_a1_10_0_, devicefami0_.device_model as device_m2_10_0_ from api_db_domestic_local.api_device_app_version_device_models devicefami0_ where devicefami0_.device_app_version_id in (select deviceappv0_.id from api_db_domestic_local.api_device_app_version deviceappv0_ where  ( ( deviceappv0_.is_version_deprecated=FALSE ) and ( deviceappv0_.is_version_deprecated=FALSE ))  and deviceappv0_.deviceApp in (select deviceapp0_.id from api_db_domestic_local.api_device_app deviceapp0_ ))";
        System.out.printf("query :: %s%n", query);
        Matcher matcher = QUERY_PATTERN.matcher(query);
        System.out.printf("matcher.matches() :: %s%n", matcher.matches());
        System.out.printf("matcher.groupCount() :: %s%n", matcher.groupCount());
    }
}
