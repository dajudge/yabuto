#! /bin/sh

${CI_PROJECT_DIR}/.docker/ci.sh mvn -s /settings.xml clean install sonar:sonar -Dsonar.login=${SONAR_TOKEN}
