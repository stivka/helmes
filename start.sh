#!/bin/bash

cd frontend

npm install

# create a concurrent instance of React
npm start &

cd ../backend

# create a concurrent instance of Spring
./gradlew bootRun &