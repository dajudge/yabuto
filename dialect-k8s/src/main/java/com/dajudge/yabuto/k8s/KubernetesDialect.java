package com.dajudge.yabuto.k8s;

import com.dajudge.yabuto.k8s.configmap.ConfigMapBuilder;
import com.dajudge.yabuto.k8s.deployment.DeploymentBuilder;
import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import com.dajudge.yabuto.api.util.MapBuilder;
import com.dajudge.yabuto.k8s.secret.OpaqueSecretBuilder;
import com.dajudge.yabuto.k8s.service.ServiceBuilder;

import java.util.Map;

import static com.dajudge.yabuto.api.util.ClosureUtil.root;

public class KubernetesDialect implements Dialect {

    static final String ENTRYPOINT_DEPLOYMENT = "deployment";
    static final String ENTRYPOINT_SERVICE = "service";
    static final String ENTRYPOINT_CONFIGMAP = "configMap";
    static final String ENTRYPOINT_SECRET_OPAQUE = "opaqueSecret";

    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new MapBuilder<String, Entrypoint>()
                .put(ENTRYPOINT_DEPLOYMENT, root(ENTRYPOINT_DEPLOYMENT, DeploymentBuilder::new))
                .put(ENTRYPOINT_SERVICE, root(ENTRYPOINT_SERVICE, ServiceBuilder::new))
                .put(ENTRYPOINT_CONFIGMAP, root(ENTRYPOINT_CONFIGMAP, ConfigMapBuilder::new))
                .put(ENTRYPOINT_SECRET_OPAQUE, root(ENTRYPOINT_SECRET_OPAQUE, OpaqueSecretBuilder::new))
                .build();
    }
}
