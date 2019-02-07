package com.dajudge.yabuto.k8s;

import com.dajudge.yabuto.k8s.deployment.DeploymentBuilder;
import com.dajudge.yabuto.api.Dialect;
import com.dajudge.yabuto.api.Entrypoint;
import com.dajudge.yabuto.api.Project;
import com.dajudge.yabuto.api.util.MapBuilder;

import java.util.Map;

import static com.dajudge.yabuto.api.util.ClosureUtil.root;

public class KubernetesDialect implements Dialect {

    static final String ENTRYPOINT_DEPLOYMENT = "deployment";

    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new MapBuilder<String, Entrypoint>()
                .put(ENTRYPOINT_DEPLOYMENT, root(DeploymentBuilder::new))
                .build();
    }
}
