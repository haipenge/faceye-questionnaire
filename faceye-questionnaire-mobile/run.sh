!#/bin/bash
git pull
mvn clean compile jetty:run -D maven.test.skip=true -P product -D jetty.port=8088
