## Superhero Container

Following instructions explain how to publish superhero via a portable Docker container.

### Pre-requisites
You need to have:
- a Docker installation available locally. See [Docker website](https://docs.docker.com/install/) on installation instructions for Windows and Linux users.
- superhero project sources locally compiled, and shell pointing to project root.


### Build image
`mvn clean package docker:build`

Now if you issue a `docker images` command you should see `springboot-jwt:latest` image listed.


### Start container

#### From Maven, default endpoint
Basic usage with defaults: server IP 127.0.0.1, server port 9090

`mvn docker:start`

#### From command line
`docker run -d -v /tmp:/tmp -p 9090:8080  --name docker-superhero superhero`

Now if you issue a `docker ps` command you should see a new running container listed.

To see logs:
`docker logs -f <CONTAINERID>`


To remove an image:
`docker rmi <IMAGE_ID> -f`
To clear all the containers:
`docker container prune`

