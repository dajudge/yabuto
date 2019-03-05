package com.dajudge.yabuto.openshift.imagestream;

import com.dajudge.yabuto.api.features.Builder;
import com.dajudge.yabuto.api.util.ObjectBuilder;
import com.dajudge.yabuto.k8s.base.RootObjectBuilder;
import com.dajudge.yabuto.openshift.shared.DockerImageFeature;
import jdk.nashorn.internal.ir.annotations.Reference;

import static com.dajudge.yabuto.api.util.SafeCasts.string;

public class ImageStreamBuilder extends RootObjectBuilder<ImageStreamBuilder> {
    public ImageStreamBuilder(final String name) {
        super("ImageStream", "v1", name);
        me().child("spec")
                .builderList("tag", "tags", TagBuilder::create);
    }

    private static class TagBuilder extends ObjectBuilder<TagBuilder> {
        private TagBuilder(final String name) {
            me().simpleValue("name", "name", name, String.class);
            me().child("from")
                    .custom("fromDockerImage", new DockerImageFeature());
            me().builder("importPolicy", "importPolicy", ImportPolicyBuilder::create);
            me().builder("referencePolicy", "referencePolicy", ReferencePolicyBuilder::create);
        }

        private static TagBuilder create(final Object[] params) {
            return new TagBuilder(string(params[0]));
        }
    }

    private static class ImportPolicyBuilder extends ObjectBuilder<ImportPolicyBuilder> {

        private ImportPolicyBuilder() {
            me().simpleValue("insecure", "insecure", null, boolean.class);
        }

        public static ImportPolicyBuilder create(final Object[] params) {
            return new ImportPolicyBuilder();
        }
    }

    private static class ReferencePolicyBuilder extends ObjectBuilder<ReferencePolicyBuilder> {
        private ReferencePolicyBuilder() {
            me().simpleValue("type", "type", null, String.class);
        }

        public static ReferencePolicyBuilder create(Object[] params) {
            return new ReferencePolicyBuilder();
        }
    }
}
