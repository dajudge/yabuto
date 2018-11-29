#! /bin/sh

${CI_PROJECT_DIR}/.docker/ci.sh mvn -s /settings.xml clean install
