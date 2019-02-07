package com.dajudge.yabuto.openshift.deploymentconfig;

import com.dajudge.yabuto.api.util.ObjectBuilder;

class ConfigChangeTriggerBuilder extends ObjectBuilder<ConfigChangeTriggerBuilder> {
    ConfigChangeTriggerBuilder() {
        me().simpleValue("type", "type", "ConfigChange", String.class);
    }
}
