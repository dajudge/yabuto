package com.dajudge.yabuto.openshift.security;

import com.dajudge.yabuto.k8s.base.RootObjectBuilder;

public class RoleBindingBuilder extends RootObjectBuilder<RoleBindingBuilder> {
    public RoleBindingBuilder(final String name) {
        super("RoleBinding", "v1", name);
        me().builderList("subject", "subjects", ObjectReferenceBuilder::create)
                .builder("roleRef", "roleRef", ObjectReferenceBuilder::create);
    }
}
