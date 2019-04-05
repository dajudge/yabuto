package com.dajudge.yabuto.openshift;

import com.dajudge.yabuto.openshift.build.BuildConfigBuilder;
import com.dajudge.yabuto.openshift.configmap.ConfigMapBuilder;
import com.dajudge.yabuto.openshift.deploymentconfig.DeploymentConfigBuilder;
import com.dajudge.yabuto.openshift.imagestream.ImageStreamBuilder;
import com.dajudge.yabuto.openshift.route.RouteBuilder;
import com.dajudge.yabuto.openshift.secret.OpaqueSecretBuilder;
import com.dajudge.yabuto.openshift.service.ServiceBuilder;
import com.dajudge.yabuto.openshift.template.TemplateBuilder;
import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import com.dajudge.yabuto.api.util.MapBuilder;

import java.util.Map;

import static com.dajudge.yabuto.api.util.ClosureUtil.root;

public class OpenShiftDialect implements Dialect {
    static final String ENTRYPOINT_DEPLOYMENTCONFIG = "deploymentConfig";
    static final String ENTRYPOINT_BUILDCONFIG = "buildConfig";
    static final String ENTRYPOINT_TEMPLATE = "template";
    static final String ENTRYPOINT_ROUTE = "route";
    static final String ENTRYPOINT_IMAGESTREAM = "imageStream";
    static final String ENTRYPOINT_SERVICE = "service";
    static final String ENTRYPOINT_CONFIGMAP = "configMap";
    static final String ENTRYPOINT_SECRET_OPAQUE = "opaqueSecret";

    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new MapBuilder<String, Entrypoint>()
                .put(ENTRYPOINT_BUILDCONFIG, root(BuildConfigBuilder::new))
                .put(ENTRYPOINT_TEMPLATE, root(TemplateBuilder::new))
                .put(ENTRYPOINT_ROUTE, root(RouteBuilder::new))
                .put(ENTRYPOINT_DEPLOYMENTCONFIG, root(DeploymentConfigBuilder::new))
                .put(ENTRYPOINT_IMAGESTREAM, root(ImageStreamBuilder::new))
                .put(ENTRYPOINT_SERVICE, root(ServiceBuilder::new))
                .put(ENTRYPOINT_CONFIGMAP, root(ConfigMapBuilder::new))
                .put(ENTRYPOINT_SECRET_OPAQUE, root(OpaqueSecretBuilder::new))
                .build();
    }
}
