# http4k-koin-exposed-poc

Make sure a PostgreSQL instance with DB name/user/password all "postgres" is up and running. i.e.:

`$ docker run --rm --name quarkus-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -p 5432:5432 -d postgres`

Run:

`$ ./gradlew run`

Build native image:

`$ ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`

Build container with native image:

`$ docker build -f Dockerfile -t quarkus-poc/v1 .`

`$ docker run -i --rm -p 8080:8080 quarkus-poc/v1`

POST example payload to `localhost:8080/capture`:

`{
"configurationId":"30c1b04c-071f-4501-abdb-3c2ee14662c1",
"authorizationReference":"2b247dbe-bb0c-4b4b-8b7c-b7fda5b3ad25",
"amount":5000,
"currency":"EUR"
}`