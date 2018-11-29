#! /bin/sh

BRANCH_NAME=`${CI_PROJECT_DIR}/.docker/ci.sh git branch | grep \* | cut -d ' ' -f2`
echo "Branch name: ${BRANCH_NAME}"

case "${BRANCH_NAME}" in
    release/*) RELEASE=true ;;
    *)  RELEASE=false ;;
esac

if [ $RELEASE = true ] ;
then
    VERSION=`echo $BRANCH_NAME | cut -d '/' -f 2`
    echo "Version: ${VERSION}"

    echo "Setting version in pom.xml..."
    ${CI_PROJECT_DIR}/.docker/ci.sh mvn -s /settings.xml versions:set -DnewVersion=${VERSION}
    echo "Publishing to bintray..."
    ${CI_PROJECT_DIR}/.docker/ci.sh mvn -s /settings.xml clean install deploy -DskipTests
else
    echo "Not publishing to bintray."
fi
