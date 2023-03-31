```shell
mvn -Puat clean install spring-boot:repackage
docker build -t org.irushu/spring-boot-demo .
docker-compose up
docker-compose down
```
