package com.dajudge.ymlgen.testapi;

import com.dajudge.ymlgen.api.util.RootObjectBuilder;
import groovy.lang.Closure;

import java.util.Map;

import static com.dajudge.ymlgen.api.util.ClosureUtil.callBuilderClosure;


public class BuildConfigBuilder extends RootObjectBuilder<BuildConfigBuilder> {
    public BuildConfigBuilder() {
        kind("BuildConfig");
        apiVersion("v1");
    }

    public BuildConfigBuilder binarySource() {
        source().put("type", "Binary");
        return this;
    }

    public BuildConfigBuilder sourceStrategy(Closure<Map<String, Object>> strategy) {
        spec().put("sourceStrategy", callBuilderClosure(strategy, new SourceStrategyBuilder()));
        return this;
    }

    public BuildConfigBuilder resources(Closure<Map<String, Object>> resources) {
        spec().put("resources", callBuilderClosure(resources, new ResourcesBuilder()));
        return this;
    }

    public BuildConfigBuilder outputToImageStreamTag(final String name) {
        outputTo().put("name", name);
        outputTo().put("kind", "ImageStreamTag");
        return this;

    }

    private Map<String, Object> outputTo() {
        return submap(submap(spec(), "output"), "to");
    }

    private Map<String, Object> source() {
        return submap(spec(), "source");
    }

    private Map<String, Object> spec() {
        return submap(object, "spec");
    }
}
