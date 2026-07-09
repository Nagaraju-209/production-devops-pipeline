#!/bin/bash

set -e

echo "====================================="
echo "| Running Trivy Docker Image Scan   |"
echo "====================================="

mkdir -p reports

trivy image \
--cache-dir .trivycache \
--timeout 15m \
--format table \    
-o reports/trivy-image-report.txt \
${IMAGE_NAME}:latest

echo "Docker image scan completed."