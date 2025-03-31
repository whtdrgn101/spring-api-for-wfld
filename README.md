# Java-21 SpringBoot Reference for RESTful API

This project serves as a reference implementation for building well organized, scalable, and maintainable RESTful API's in Java using the SpringBoot framework.  It follows SpringBoot best practice for separation of concerns and leverages OpenTelemetry and Reseliency4j to make the API's supportable and fault-tolerant.  

### Project Organization:

The project package structure is organized around the resources being served by the API.  This lays out the packages as follows:

- com.wfld.*DOMAIN*.*APP_NAME*
  |_> *RESOURCE*
    |_> controllers
    |_> entities
    |_> repositories
    |_> services
  |_> config

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.3/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.3/gradle-plugin/packaging-oci-image.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.4.3/reference/web/reactive.html)
* [OAuth2 Client](https://docs.spring.io/spring-boot/3.4.3/reference/web/spring-security.html#web.security.oauth2.client)
* [Spring Data R2DBC](https://docs.spring.io/spring-boot/3.4.3/reference/data/sql.html#data.sql.r2dbc)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Accessing data with R2DBC](https://spring.io/guides/gs/accessing-data-r2dbc/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [R2DBC Homepage](https://r2dbc.io)
