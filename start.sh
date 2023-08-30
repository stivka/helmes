#!/bin/bash

cd backend

# Download dependencies
./gradlew dependencies

# Run tests
./gradlew test

# Uncomment this to have failing tests cancel application launch
# if [ $? -ne 0 ]; then
#   echo "Tests failed, not starting the application"
#   exit 1
# fi

# Start Spring application
./gradlew bootRun &

cd ../frontend

# Download dependencies and start React application
npm install
npm start &
