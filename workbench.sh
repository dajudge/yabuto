#! /bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"

LOCAL=-it CI_PROJECT_DIR=$DIR $DIR/.docker/ci.sh $@
