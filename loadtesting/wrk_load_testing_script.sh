#!/bin/bash

docker run --rm -v C:/Users/evgeniy.m/IdeaProjects/20k-rps-architecture/loadtesting:/data \
      williamyeh/wrk \
	  -t8 -c2500 -d40s -s "load_testing_20krps.lua" http://host.docker.internal:8090/gps-tracker

$SHELL