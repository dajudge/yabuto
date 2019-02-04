#! /bin/sh

echo "Branch name: ${CI_COMMIT_REF_NAME}"

case "${CI_COMMIT_REF_NAME}" in
    release/*) RELEASE=true ;;
    *)  RELEASE=false ;;
esac

if [ $RELEASE = true ] ;
then
    VERSION=`echo $CI_COMMIT_REF_NAME | cut -d '/' -f 2`
    echo "Version: ${VERSION}"
    echo "Bintray API user: $BINTRAY_API_USER"

    echo "Setting version in pom.xml..."
    ${CI_PROJECT_DIR}/.docker/ci-env.sh ${CI_PROJECT_DIR}/.docker/ci.sh mvn versions:set -DnewVersion=${VERSION}
    echo "Publishing to bintray..."
    ${CI_PROJECT_DIR}/.docker/ci-env.sh ${CI_PROJECT_DIR}/.docker/ci.sh mvn clean install deploy -DskipTests
else
    echo "Not publishing to bintray."
fi
