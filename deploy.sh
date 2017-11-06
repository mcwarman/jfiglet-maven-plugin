#!/usr/bin/env bash
set -e
PROFILES=release-artefacts 
if [ "$TRAVIS_BRANCH" = 'master' ]; then
  PROFILES="$PROFILES,sign"
fi
if [[ $TRAVIS_BRANCH =~ ^master|develop$ ]] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
  echo mvn deploy -P$PROFILES -DskipTests=true -Dinvoker.skip=true -Djacoco.skip=true --settings .travis-m2-settings.xml -B
fi
