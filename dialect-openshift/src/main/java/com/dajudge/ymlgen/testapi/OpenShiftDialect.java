package com.dajudge.ymlgen.testapi;

import com.dajudge.ymlgen.api.Dialect;
import com.dajudge.ymlgen.api.Entrypoint;
import com.dajudge.ymlgen.api.Project;

import java.util.HashMap;
import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.root;

public class OpenShiftDialect implements Dialect {
    @Override
    public Map<String, Entrypoint> getEntrypoints(final Project project) {
        return new HashMap<String, Entrypoint>() {{
            put("buildConfig", root(BuildConfigBuilder::new));
            put("template", root(TemplateBuilder::new));
            put("route", root(RouteBuilder::new));
        }};
    }
}
