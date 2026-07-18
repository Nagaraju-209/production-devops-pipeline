#!/bin/bash

set -e

echo "Stopping Compose services..."
docker compose -f docker-compose/docker-compose.yml down

echo "Cleanup completed."