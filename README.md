# http4k-koin-exposed-poc

In order to use the POST endpoint make sure a PostgreSQL instance with DB name/user/password all "postgres" is up and running. i.e.:

`$ docker run --rm --name quarkus-db -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -p 5432:5432 -d postgres`

But there is also a GET endpoint for quick testing purposes.

Run:

`$ ./gradlew run`

Build native image and run:

`$ ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`

`$ cd build`

`$ ./http4k-koin-poc-1.0.0-SNAPSHOT-runner`

OR Build container with native image and run:

`$ docker build -f Dockerfile -t quarkus-poc/v1 .`

`$ docker run -i --rm -p 8080:8080 quarkus-poc/v1`

POST example payload to `localhost:8080/capture`:

`{
"configurationId":"30c1b04c-071f-4501-abdb-3c2ee14662c1",
"authorizationReference":"2b247dbe-bb0c-4b4b-8b7c-b7fda5b3ad25",
"amount":5000,
"currency":"EUR"
}`