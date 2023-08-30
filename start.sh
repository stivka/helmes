#!/bin/bash

cd backend

./gradlew test

# Uncomment this to have failing tests cancel application launch
# if [ $? -ne 0 ]; then
#   echo "Tests failed, not starting the application"
#   exit 1
# fi

# create a concurrent instance of Spring
./gradlew bootRun &

cd ../frontend

npm install

# create a concurrent instance of React
npm start &
