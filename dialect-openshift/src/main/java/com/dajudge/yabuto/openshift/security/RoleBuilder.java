package com.dajudge.yabuto.openshift.security;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class RoleBuilder extends RootObjectBuilder<RoleBuilder> {
    public RoleBuilder(final String name) {
        super("Role", "v1", name);
        me().builderList("rule", "rules", RuleBuilder::create);
    }
}
