#!/bin/bash

set -e

echo "Starting container..."

docker run -d \
--name ${CONTAINER_NAME} \
-p ${APP_PORT}:${APP_PORT} \
${IMAGE_NAME}:${BUILD_NUMBER}

echo "Container started."