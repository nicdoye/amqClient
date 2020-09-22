# amqClient

Run via

```
mvn spring-boot:run -Dspring-boot.run.arguments="ssl://localhost:61617,username,password"
```

or

```
mvn clean install spring-boot:repackage
java -jar target/amq-client-0.0.2-SNAPSHOT.jar ssl://localhost:61617 user pass
```

Alternatively, build locally via the following, and copy the jar file to whereever:

```
mvn clean install spring-boot:repackage
```