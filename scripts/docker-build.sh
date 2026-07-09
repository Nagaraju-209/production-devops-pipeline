#!/bin/bash

set -e

echo "Building Docker image..."

docker build \
-f docker/Dockerfile \
-t ${IMAGE_NAME}:${BUILD_NUMBER} \
-t ${IMAGE_NAME}:latest .

echo "Docker image built successfully."