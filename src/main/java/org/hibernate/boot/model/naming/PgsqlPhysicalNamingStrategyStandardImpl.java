package org.hibernate.boot.model.naming;

import java.io.Serializable;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PgsqlPhysicalNamingStrategyStandardImpl extends PhysicalNamingStrategyStandardImpl
        implements Serializable {

    public static final PgsqlPhysicalNamingStrategyStandardImpl INSTANCE =
            new PgsqlPhysicalNamingStrategyStandardImpl();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(name.getText().toLowerCase(), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return Identifier.toIdentifier(name.getText().toLowerCase(), name.isQuoted());
    }
}
