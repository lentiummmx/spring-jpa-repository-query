package org.hibernate.boot.model.naming;

import java.io.Serializable;
import java.util.Locale;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PgsqlPhysicalNamingStrategyStdImpl extends PhysicalNamingStrategyStandardImpl implements Serializable {

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        // return super.toPhysicalTableName(name, context);
        return new Identifier(name.getText().toLowerCase(Locale.ROOT), name.isQuoted());
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
        // return super.toPhysicalColumnName(name, context);
        return new Identifier(name.getText().toLowerCase(Locale.ROOT), name.isQuoted());
    }
}
