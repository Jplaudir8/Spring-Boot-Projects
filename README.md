# Projects
### Cloud Storage Service Company
This project consists of a cloud storage service which will enable the user:
- Simple File Storage: Upload, download, and remove files
- Note Management: Add, update, and remove text notes
- Password Management: Save, edit, and delete website credentials.

# Other
### Review Exercises
This directory contains some exercises that have minor configurations and added java code to help review Spring Boot concepts seen in past courses. Therefore, do not see this ***Review Exercises*** directory as a container of personal projects but as one for concept review exercises.

Content:
- DogRestAPI: REST API that returns a list of dogs from an embedded H2 in-memory database.
- DogGraphQL: GraphQL API that returns a list of dogs from an embedded H2 in-memory database.Trying GraphQL queries such as:
        
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
