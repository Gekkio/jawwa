#!/bin/bash

if [ -z "$1" ]; then
  echo "Usage: ./set-versions.sh <version>"
  exit 1
fi

VERSION=$1

DISABLED="jrebel" mvn versions:set -DnewVersion=$VERSION versions:commit
