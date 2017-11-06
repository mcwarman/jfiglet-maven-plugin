# jfiglet-maven-plugin [![Build Status](https://travis-ci.org/mcwarman/jfiglet-maven-plugin.svg?branch=develop)](https://travis-ci.org/mcwarman/jfiglet-maven-plugin) [![codecov](https://codecov.io/gh/mcwarman/jfiglet-maven-plugin/branch/develop/graph/badge.svg)](https://codecov.io/gh/mcwarman/jfiglet-maven-plugin) [![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

```
$ mvn io.github.mcwarman:jfiglet-maven-plugin:1.0.0-SNAPSHOT:generate -Dmessage="jfiglet-maven-plugin"
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building Maven Stub Project (No POM) 1
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- jfiglet-maven-plugin:1.0.0-SNAPSHOT:generate (default-cli) @ standalone-pom ---
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
[INFO] Total time: 0.740 s
[INFO] Finished at: 2017-09-26T10:59:21+01:00
[INFO] Final Memory: 8M/245M
[INFO] ------------------------------------------------------------------------
```

## Performing Release

```
git flow release start 0.0.1
mvn versions:set -DnewVersion=0.0.1
git flow release publish 0.0.1
git flow release finish 0.0.1
mvn versions:set -DnewVersion=0.0.2-SNAPSHOT
git push --all origin
git push --tags
```
