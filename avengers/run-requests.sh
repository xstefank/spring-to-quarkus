#!/bin/bash -x

http :8080/avengers
http :8080/avengers/Thor
http :8080/avengers/Hulk
cat avenger.json | http POST :8080/avengers
http :8080/avengers
http :8080/avengers/Sentry

