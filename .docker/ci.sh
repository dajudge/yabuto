#! /bin/sh

echo "CI_PROJECT_DIR: ${CI_PROJECT_DIR}"

docker build -t builder_jdk8 ${CI_PROJECT_DIR}/.docker/builder
docker run --rm \
    --net host \
    -e BINTRAY_API_USER:${BINTRAY_API_USER} \
    -e BINTRAY_API_KEY:${BINTRAY_API_KEY} \
    -v "${CI_PROJECT_DIR}":/project \
    -v /var/run/docker.sock:/var/run/docker.sock \
    -v "${CI_PROJECT_DIR}/.cache/.m2":/root/.m2 \
    --workdir /project \
    builder_jdk8 \
    $@
