#! /bin/bash

BRANCH_NAME=`git branch | grep \* | cut -d ' ' -f2`
echo "Branch name: ${BRANCH_NAME}"
VERSION=`echo $BRANCH_NAME | cut -d '/' -f 2`
echo "Version: ${VERSION}"

if  [[ $BRANCH_NAME == release/* ]] ;
then
    echo "Setting version in pom.xml..."
    echo "Publishing to bintray..."
    ${CI_PROJECT_DIR}/.docker/ci.sh mvn -s /settings.xml versions:set -DnewVersion=${VERSION}
    ${CI_PROJECT_DIR}/.docker/ci.sh mvn -s /settings.xml clean install deploy -DskipTests
else
    echo "Not publishing to bintray."
fi
