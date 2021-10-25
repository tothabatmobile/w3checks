### W3Checks how to use repo

* __One Click Selenium Grid:__ With a single command, [Docker Compose](https://docs.docker.com/compose/overview/) is utilised to create an isolated Selenium Grid environment.


#### Running local:
__Setup__

There is one system properties before triggering a test build

_Dbrowser_

There are a 2 different browser options:


`-Dbrowser=chrome`

`-Dbrowser=firefox`

#### Running on Docker:

To execute all tests on docker

`docker-compose up`

`./gradlew clean cucumber -Dbrowser=firefox-docker`

`./gradlew clean cucumber -Dbrowser=chrome-docker`

To shut down docker

`docker-compose down`

#### Reports:
See the report at: /w3checks/build/reports/tests/cucumber/index.html

