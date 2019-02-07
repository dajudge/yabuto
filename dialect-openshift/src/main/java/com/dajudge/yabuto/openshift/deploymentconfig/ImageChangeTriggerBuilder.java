package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.openshift.shared.ImageStreamTagFeature;
import com.dajudge.yabuto.api.util.ObjectBuilder;

class ImageChangeTriggerBuilder extends ObjectBuilder<ImageChangeTriggerBuilder> {
    ImageChangeTriggerBuilder() {
        me().simpleValue("type", "type", "ImageChange", String.class);
        me().child("imageChangeParams")
                .simpleList("container", "containerNames", String.class)
                .simpleValue("automatic", "automatic", null, boolean.class)
                .child("from").custom("fromImageStreamTag", new ImageStreamTagFeature());
    }
}
