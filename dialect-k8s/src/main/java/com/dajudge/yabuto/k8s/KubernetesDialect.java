package com.dajudge.yabuto.k8s;

import com.dajudge.ymlgen.api.Dialect;
import com.dajudge.ymlgen.api.Entrypoint;
import com.dajudge.ymlgen.api.Project;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.root;

public class KubernetesDialect implements Dialect {
    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new HashMap<String, Entrypoint>() {{
            put("deploymentConfig", root(DeploymentConfigBuilder::new));
        }};
    }
}
