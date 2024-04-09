# Spring to Quarkus
Copied from https://github.com/edeandrea/quarkus-for-spring-devs-examples for demo purposes.

## Start up Database
This application expects a PostgreSQL database running on localhost. You can use Docker to start the database:

```shell
docker run -it --rm=true --name database -p 5432:5432 -e POSTGRES_USER=fruits -e POSTGRES_PASSWORD=fruits -e POSTGRES_DB=fruits postgres:14
```
