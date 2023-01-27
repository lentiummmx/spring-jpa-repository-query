package org.springframework.boot.orm.jpa.hibernate;

import java.io.Serializable;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PgsqlSpringPhysicalNamingStrategyImpl extends SpringPhysicalNamingStrategy implements Serializable {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(name.getText().toLowerCase(), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(name.getText().toLowerCase(), name.isQuoted());
    }
    /*
     * @Override protected boolean isCaseInsensitive(JdbcEnvironment jdbcEnvironment) {
     * return super.isCaseInsensitive(jdbcEnvironment); }
     */

}
