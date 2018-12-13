package com.dajudge.yabuto.k8s;

import com.dajudge.yabuto.k8s.deployment.DeploymentBuilder;
import com.dajudge.ymlgen.api.Dialect;
import com.dajudge.ymlgen.api.Entrypoint;
import com.dajudge.ymlgen.api.Project;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.root;

public class KubernetesDialect implements Dialect {

    static final String ENTRYPOINT_DEPLOYMENT = "deployment";

    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new HashMap<String, Entrypoint>() {{
            put(ENTRYPOINT_DEPLOYMENT, root(DeploymentBuilder::new));
        }};
    }
}
