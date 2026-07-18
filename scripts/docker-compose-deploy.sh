#!/bin/bash

set -e

echo "Stopping existing Compose stack..."
docker compose -f docker-compose/docker-compose.yml down

echo "Building and starting services..."
docker compose -f docker-compose/docker-compose.yml up -d

echo "Deployment completed."