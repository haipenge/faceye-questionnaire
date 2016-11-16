!#/bin/bash
git pull
mvn clean compile war:war -D maven.test.skip=true -P product
sh /data/tools/resin/resin-faceye-emergency-mobile/bin/resin.sh stop
cp target/*.war /data/deploy/faceye-emergency-mobile
sh /data/tools/resin/resin-faceye-emergency-mobile/bin/resin.sh start
