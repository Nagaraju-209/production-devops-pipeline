#!/bin/bash

set -e

echo "Waiting for application..."

for i in {1..20}
do
    if curl -s http://localhost:${APP_PORT}/actuator/health | grep '"status":"UP"' > /dev/null
    then
        echo "Application is healthy."
        exit 0
    fi

    sleep 5
done

echo "Health check failed."

exit 1