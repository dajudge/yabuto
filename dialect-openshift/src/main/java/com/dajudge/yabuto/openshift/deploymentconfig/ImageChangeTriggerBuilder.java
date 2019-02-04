package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.ymlgen.api.util.ObjectBuilder;

class ImageChangeTriggerBuilder extends ObjectBuilder<ImageChangeTriggerBuilder> {
    ImageChangeTriggerBuilder() {
        me()
                .simpleList("container", "containerNames", String.class)
                .simpleValue("type", "type", "ImageChange", String.class)
                .simpleValue("automatic", "automatic", null, boolean.class);
    }
}
