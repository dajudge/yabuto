#! /bin/sh

echo "CI_PROJECT_DIR: ${CI_PROJECT_DIR}"

if [ -f "$CI_PROJECT_DIR/.config" ]; then
    echo "Importing $CI_PROJECT_DIR/.config"
    . $CI_PROJECT_DIR/.config
fi

IMG=$(docker build -q ${CI_PROJECT_DIR}/.docker/builder \
    --build-arg http_proxy="$http_proxy" \
    --build-arg https_proxy="$https_proxy" \
    --build-arg no_proxy="$no_proxy")

if [ "" == "${MAVEN_SETTINGS}" ]; then
    if [ -f "${HOME}/.m2/settings.xm"l ]; then
        MAVEN_SETTINGS=${HOME}/.m2/settings.xml
    fi
fi

if [ "" == "${MAVEN_HOME}" ]; then
    if [ -d "${HOME}/.m2" ]; then
        MAVEN_HOME="${HOME}/.m2"
    fi
else
    mkdir -p "${MAVEN_HOME}"
fi

if [ "" != "${MAVEN_HOME}" ]; then
    echo "Mounting $MAVEN_HOME as maven home."
    MAVEN_HOME_MOUNT="-v ${MAVEN_HOME_MOUNT}:${HOME}/.m2"
    if [ "" != "${MAVEN_SETTINGS}" ]; then
        MAVEN_SETTINGS_MOUNT="-v ${MAVEN_SETTINGS}:${HOME}/.m2/settings.xml"
        echo "Mounting $MAVEN_SETTINGS as maven settings.xml."
    else
        echo "Not mounting maven settings.xml."
    fi
else
    echo "Not mounting maven home directory."
fi

if [ "" != "${MAVEN_OPTS}" ]; then
    echo "Applying MAVEN_OPTS=$MAVEN_OPTS"
    MAVEN_OPTS_ENV="-e MAVEN_OPTS=${MAVEN_OPTS}"
fi

mkdir -p ${CI_PROJECT_DIR}/.cache/.sonar
docker run --rm \
    --net host \
    -u $(id -u):$(id -g) \
    -v /etc/passwd:/etc/passwd \
    -v /etc/group:/etc/group \
    -v ${HOME}:${HOME} \
    -e HOME:${HOME} \
    -e http_proxy="$http_proxy" \
    -e https_proxy="$https_proxy" \
    -e no_proxy="$no_proxy" \
    -v "${CI_PROJECT_DIR}":/project \
    $MAVEN_OPTS_ENV \
    $MAVEN_HOME_MOUNT \
    $MAVEN_SETTINGS_MOUNT \
    -v "${CI_PROJECT_DIR}/.cache/.sonar":${HOME}/.sonar \
    -v /var/run/docker.sock:/var/run/docker.sock \
    --workdir /project \
    $LOCAL $IMG \
    $@
