#!/bin/bash

docker run --rm -v C:/Users/evgeniy.m/IdeaProjects/20k-rps-architecture/loadtesting:/data \
      williamyeh/wrk \
	  -t8 -c80 -d2m -s "load_testing_20krps.lua" http://host.docker.internal:8090/gps-tracker

$SHELL