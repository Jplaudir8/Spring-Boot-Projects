## DogGraphQL

Tried GraphQL queries such as:
        
(Postman Syntax)
```GraphQL
{
   "query":"{ findAllDogs {id name breed origin} }”
}

{
   "query":"{ findDogById(id:4) {id name} }”
}

{
   "query":"mutation { updateDogName(newName: \"Lenka\", id:4) {id name} }"
}
```
(GraphiQL Syntax)
```GraphQL
query {
   findAllDogs {
     id
     name
     breed
     origin
   }
}

query {
   findDogById(id:2) {
      id
      name
      breed
      origin
    }
}
```

## Microservices
For this exercise I have created an Eureka Server and just one microservice which retrieves information about dogs.

Architecture for this exercise:
<p align="center">
    <img src="https://github.com/Jplaudir8/Spring-Boot-Projects/blob/master/Review-Exercises/Microservices/MSA%20Image.png" width="600" alt="" title="Color Scheme Used">
</p>

- Do not forget that if a microservice is not registered as an Eureka client, then:
  1. The microservice cannot be discovered.
  2. Clients of the microservice must use the host name and port to access the service.
