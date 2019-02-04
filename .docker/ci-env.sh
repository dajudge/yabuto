#! /bin/sh

MAVEN_HOME=${CI_PROJECT_DIR}/.cache/.m2 MAVEN_SETTINGS=${CI_PROJECT_DIR}/.docker/settings.xml $@