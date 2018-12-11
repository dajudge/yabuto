#! /bin/sh

echo "CI_PROJECT_DIR: ${CI_PROJECT_DIR}"

docker run --rm \
    --net host \
    -u $(id -u):$(id -g) \
    -v /etc/passwd:/etc/passwd \
    -v /etc/group:/etc/group \
    -v ${HOME}:${HOME} \
    -e HOME:${HOME} \
    -e MAVEN_CONFIG=${HOME}/.m2 \
    -v "${CI_PROJECT_DIR}":/project \
    -v "${CI_PROJECT_DIR}/.cache/.m2":${HOME}/.m2 \
    -v "${CI_PROJECT_DIR}/.cache/.sonar":${HOME}/.sonar \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --workdir /project \
    -it $(docker build -q ${CI_PROJECT_DIR}/.docker/builder) \
    $@
