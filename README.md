## A Spring Boot Application for Securing a REST API with JSON Web Token (JWT)


## Main building blocks
 * Spring Boot 1.5.9.RELEASE - http://docs.spring.io/spring-boot/docs/1.5.9.RELEASE/reference/htmlsingle/ 
 * JSON Web Token go to https://jwt.io/ to decode your generated token and learn more
 * Mysql


## To run the application
Use one of the several ways of running a Spring Boot application. Below are just three options:

1. Build using maven goal: `mvn clean package` and execute the resulting artifact as follows `java -jar superhero-1.0-SNAPSHOT.jar` or
2. On Unix/Linux based systems: run `mvn clean package` then run the resulting jar as any other executable `./superhero-1.0-SNAPSHOT.jar`
3. Build and start as a Docker container. Instructions at: [README](src/main/docker/README.md)


## To test the application

 ### First you will need the following basic pieces of information:

 * client: fiXty1x4WHtVZha
 * secret: payworks-secret
 * Admin user: admin/admin or  payworks/payworks
 * Example of resource accessible to all authenticated users:  http://localhost:8080/api/superheroes

 1. Generate an access token

    http://54.212.194.122:8080/oauth/token?grant_type=password&username=admin&password=admin
    or
    http://localhost:8080/oauth/token?grant_type=password&username=admin&password=admin

    `
    {
        "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicGF5d29ya3NyZXNvdXJjZWlkIl0sInVzZXJfbmFtZSI6InN0ZWZhbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJleHAiOjE1MjE2MjA3NTQsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiODk2YWYyNDMtZWNhNS00Y2FlLTk3YzUtOGYyMTgzY2EwNzc0IiwiY2xpZW50X2lkIjoicGF5d29ya3MifQ.HSg8qTcOgCUOBjzlxwNtVzM1UGwGqwUbVaWiAj6Skhs",
        "token_type": "bearer",
        "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsicGF5d29ya3NyZXNvdXJjZWlkIl0sInVzZXJfbmFtZSI6InN0ZWZhbiIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdLCJhdGkiOiI4OTZhZjI0My1lY2E1LTRjYWUtOTdjNS04ZjIxODNjYTA3NzQiLCJleHAiOjE1MjQxNjk1NTQsImF1dGhvcml0aWVzIjpbIlJPTEVfQURNSU4iXSwianRpIjoiODBhZjIyMjYtODg4My00NzA2LTk2NmEtOTUxMGQxOThkOTZjIiwiY2xpZW50X2lkIjoicGF5d29ya3MifQ.g_lBqNrISKHgScdV1sAYxX-dJwrJ64hNxZ2jQW2N1EM",
        "expires_in": 43199,
        "scope": "read write",
        "jti": "896af243-eca5-4cae-97c5-8f2183ca0774"
    }`
    
 1a. To regenerate an access token if needed:
    http://54.212.194.122:8080/oauth/token?grant_type=refresh_token&refresh_token=
    

 2. Use token as: Bearer 'Token' to: 
   - create a superhero:
      http://54.212.194.122:8080/api/superheroes
      {
        "name": "Payworks",
        "pseudonym": "Munchen",
        "publisher": "Payworks",
        "skills" : [
            {"name" :"Awesome"}
        ]
      }    and
      
      {
        "name": "Joe",
        "pseudonym": "Joe",
        "publisher": "Marvel",
        "allies": [{"name": "Payworks"}]    
      }

      
   - get a superhero:
      http://54.212.194.122:8080/api/superheroes/Payworks
   - get all superheroes
      http://54.212.194.122:8080/api/superheroes