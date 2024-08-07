# Spring Data - Fragment SPI Example

This project contains a sample using `spring.factories` to register implementation details for a repository extension for MongoDB Vector Search that lives outside of the project namespace. 

The project is divided into the `atlas-api`, providing the extension, and the `sample` using it.

## atlas-api

The `AtlasRepository` is the base interface containing a `vectorSearch` method that is implemented in `AtlasRepositoryFragment`. The configuration in `src/main/resources/META-INF/spring.factories` makes sure it is picked up by the spring data infrastructure.

The implementation leverages `RepositoryMethodContext` to get hold of method invocation metadata to determine the collection name derived from the repositories domain type `<T>`.
Since providing the metadata needs to be explicitly activated a `BeanPostProcessor` doing so is automatically registered with Spring Boot applications via `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports`.

## sample

The `MovieRepository` extends the `AtlasRepository` from the api project using a `Movie` type targeting the `movies` collection. No further configuration is needed to use the provided `vectorSearch` within the `MovieRepositoryTests`.

The `Movies` class in `src/main/test` takes care of setting up required test data and indexes.
