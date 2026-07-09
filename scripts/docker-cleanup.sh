#!/bin/bash

set -e

echo "Removing existing container..."

docker rm -f ${CONTAINER_NAME} || true

echo "Cleanup completed."