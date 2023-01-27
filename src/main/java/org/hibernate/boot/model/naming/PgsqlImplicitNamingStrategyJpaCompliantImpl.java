package org.hibernate.boot.model.naming;

import java.io.Serializable;

public class PgsqlImplicitNamingStrategyJpaCompliantImpl extends ImplicitNamingStrategyJpaCompliantImpl
        implements Serializable {

    public static final PgsqlImplicitNamingStrategyJpaCompliantImpl INSTANCE =
            new PgsqlImplicitNamingStrategyJpaCompliantImpl();

    @Override
    public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
        Identifier name = super.determinePrimaryTableName(source);
        return Identifier.toIdentifier(name.getText().toLowerCase(), name.isQuoted());
    }

    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        Identifier name = super.determineBasicColumnName(source);
        return Identifier.toIdentifier(name.getText().toLowerCase(), name.isQuoted());
    }
}
