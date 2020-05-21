# jfiglet-maven-plugin [![Build Status](https://travis-ci.org/mcwarman/jfiglet-maven-plugin.svg?branch=develop)](https://travis-ci.org/mcwarman/jfiglet-maven-plugin) ![build](https://github.com/mcwarman/jfiglet-maven-plugin/workflows/build/badge.svg) [![codecov](https://codecov.io/gh/mcwarman/jfiglet-maven-plugin/branch/develop/graph/badge.svg)](https://codecov.io/gh/mcwarman/jfiglet-maven-plugin) [![Known Vulnerabilities](https://snyk.io/test/github/mcwarman/jfiglet-maven-plugin/badge.svg)](https://snyk.io/test/github/mcwarman/jfiglet-maven-plugin) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/aae25cbf7e064d0095c45867462b10a6)](https://www.codacy.com/app/mcwarman/jfiglet-maven-plugin?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=mcwarman/jfiglet-maven-plugin&amp;utm_campaign=Badge_Grade) [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT) [![GitHub tag](https://img.shields.io/github/tag/mcwarman/jfiglet-maven-plugin.svg)](https://github.com/mcwarman/jfiglet-maven-plugin/tags) [![Maven Central](https://img.shields.io/maven-central/v/io.github.mcwarman/jfiglet-maven-plugin.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22io.github.mcwarman%22%20AND%20a%3A%22jfiglet-maven-plugin%22) [![Javadocs](http://javadoc.io/badge/io.github.mcwarman/jfiglet-maven-plugin.svg?color=blue)](http://javadoc.io/doc/io.github.mcwarman/jfiglet-maven-plugin) ![Uses Badges](https://img.shields.io/badge/likes-badges-blue.svg) [![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=adaptris/interlok-service-tester)](https://dependabot.com)

## Introduction

Maven plugin which wraps [jfiglet](https://github.com/lalyos/jfiglet) to allow execution for "reasons", maybe you could include the a version number figlet'd in your release notes.

## Sample Execution

```
$  mvn io.github.mcwarman:jfiglet-maven-plugin:0.0.2:generate -Dmessage="jfiglet-maven-plugin"
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- jfiglet-maven-plugin:0.0.2:generate (default-cli) @ standalone-pom ---
[INFO]
    _    __   _           _          _                                                                        _                   _
   (_)  / _| (_)   __ _  | |   ___  | |_           _ __ ___     __ _  __   __   ___   _ __            _ __   | |  _   _    __ _  (_)  _ __
   | | | |_  | |  / _` | | |  / _ \ | __|  _____  | '_ ` _ \   / _` | \ \ / /  / _ \ | '_ \   _____  | '_ \  | | | | | |  / _` | | | | '_ \
   | | |  _| | | | (_| | | | |  __/ | |_  |_____| | | | | | | | (_| |  \ V /  |  __/ | | | | |_____| | |_) | | | | |_| | | (_| | | | | | | |
  _/ | |_|   |_|  \__, | |_|  \___|  \__|         |_| |_| |_|  \__,_|   \_/    \___| |_| |_|         | .__/  |_|  \__,_|  \__, | |_| |_| |_|
 |__/             |___/                                                                              |_|                  |___/

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 0.679 s
[INFO] Finished at: 2018-06-07T11:14:43+01:00
[INFO] Final Memory: 8M/245M
[INFO] ------------------------------------------------------------------------
```

## Performing Release

```
git flow release start 0.0.3
mvn versions:set -DgenerateBackupPoms=false -DnewVersion=0.0.3
git commit -a -m "Increment release [0.0.3]"
git flow release finish 0.0.3
mvn versions:set -DgenerateBackupPoms=false -DnewVersion=0.0.4-SNAPSHOT
git commit -a -m "Increment snapshot release [0.0.4-SNAPSHOT]"
git push --mirror
```
