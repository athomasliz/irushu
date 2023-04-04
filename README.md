```shell
// Go to spring-boot/service-login
mvn -Puat clean install spring-boot:repackage
docker build -t irushu/service-login .

// Go to spring-boot/service-demo
mvn -Puat clean install spring-boot:repackage
docker build -t irushu/service-demo .

// Go to react/web-demo
docker build -t irushu/web-demo .

// Run below
docker-compose up

// Go to below links and test
http://localhost:3001

// Swagger page
https://localhost:20000/swagger-ui/index.html
https://localhost:20001/swagger-ui/index.html
```
