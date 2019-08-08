package com.dajudge.yabuto.openshift;

import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import com.dajudge.yabuto.api.util.MapBuilder;
import com.dajudge.yabuto.openshift.build.BuildConfigBuilder;
import com.dajudge.yabuto.openshift.deploymentconfig.DeploymentConfigBuilder;
import com.dajudge.yabuto.openshift.imagestream.ImageStreamBuilder;
import com.dajudge.yabuto.openshift.route.RouteBuilder;
import com.dajudge.yabuto.openshift.security.RoleBindingBuilder;
import com.dajudge.yabuto.openshift.security.RoleBuilder;
import com.dajudge.yabuto.openshift.security.ServiceAccountBuilder;
import com.dajudge.yabuto.openshift.template.TemplateBuilder;

import java.util.Map;

import static com.dajudge.yabuto.api.util.ClosureUtil.root;

public class OpenShiftDialect implements Dialect {
    static final String ENTRYPOINT_DEPLOYMENTCONFIG = "deploymentConfig";
    static final String ENTRYPOINT_BUILDCONFIG = "buildConfig";
    static final String ENTRYPOINT_TEMPLATE = "template";
    static final String ENTRYPOINT_ROUTE = "route";
    static final String ENTRYPOINT_IMAGESTREAM = "imageStream";
    static final String ENTRYPOINT_ROLE = "role";
    static final String ENTRYPOINT_SERVICEACCOUNT = "serviceAccount";
    static final String ENTRYPOINT_ROLEBINDING = "roleBinding";

    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new MapBuilder<String, Entrypoint>()
                .put(ENTRYPOINT_BUILDCONFIG, root(ENTRYPOINT_BUILDCONFIG, BuildConfigBuilder::new))
                .put(ENTRYPOINT_TEMPLATE, root(ENTRYPOINT_TEMPLATE, TemplateBuilder::new))
                .put(ENTRYPOINT_ROUTE, root(ENTRYPOINT_ROUTE, RouteBuilder::new))
                .put(ENTRYPOINT_DEPLOYMENTCONFIG, root(ENTRYPOINT_DEPLOYMENTCONFIG, DeploymentConfigBuilder::new))
                .put(ENTRYPOINT_IMAGESTREAM, root(ENTRYPOINT_IMAGESTREAM, ImageStreamBuilder::new))
                .put(ENTRYPOINT_ROLE, root(ENTRYPOINT_ROLE, RoleBuilder::new))
                .put(ENTRYPOINT_SERVICEACCOUNT, root(ENTRYPOINT_SERVICEACCOUNT, ServiceAccountBuilder::new))
                .put(ENTRYPOINT_ROLEBINDING, root(ENTRYPOINT_ROLEBINDING, RoleBindingBuilder::new))
                .build();
    }
}
