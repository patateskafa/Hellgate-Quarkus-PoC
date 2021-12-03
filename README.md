# http4k-koin-poc

Run:

`$ ./gradlew run`

Build native image:

`$ ./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`

Build container with native image:

`$ docker build -f Dockerfile -t quarkus-poc/v1 .`

`$ docker run -i --rm -p 8080:8080 quarkus-poc/v1`
