#/bin/sh

docker run -it --rm --name avengers-database -p 5432:5432 -e POSTGRES_USER=avengers -e POSTGRES_PASSWORD=avengers -e POSTGRES_DB=avengers postgres:17
