#! /bin/sh

${CI_PROJECT_DIR}/.docker/ci-env.sh ${CI_PROJECT_DIR}/.docker/ci.sh mvn clean install sonar:sonar -Dsonar.login=${SONAR_TOKEN}
