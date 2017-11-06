#!/usr/bin/env bash
set -e
if [ "$TRAVIS_BRANCH" = 'master' ] && [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
  openssl aes-256-cbc -K $encrypted_0b78ed9cdbf8_key -iv $encrypted_0b78ed9cdbf8_iv -in .travis-codesigning.asc.enc -out .travis-codesigning.asc -d 
  gpg --fast-import .travis-codesigning.asc
fi
