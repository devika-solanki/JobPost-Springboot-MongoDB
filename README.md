# Java REST API using Spring Boot and MongoDB

This repository contains a Java REST API project built with Spring Boot that interacts with a MongoDB database. This README provides an overview of the project, instructions for setting it up, and details on how to use the API.<br>

**Screenshots regrading how application responds to requests using postman and swagger.api is uploded in Screenshots folder of given repo**<br>
You can also use Swagger Api instead of Postman and do the related config for Swagger<br>

### Prerequisites
Before you can run this project, you need to have the following software and tools installed on your system:<br>
Java JDK (Java 8 or higher)<br>
Apache Maven<br>
MongoDB (Make sure MongoDB is running and accessible or you can use Mongo Atlas and create your collection there)<br>

### Project Structure
src/main/java/com/devika/Joblisting: This is where your Java source code for the Spring Boot application is located.<br>
src/main/resources: Configuration files, including application.properties for application settings.<br>
src/test: Contains unit and integration tests.<br>
.gitignore: Specifies files and directories to be ignored by Git.<br>
pom.xml: Maven project configuration file.<br>

### Configuration
Before running the application, you should configure the MongoDB connection settings in the application.properties file located in the src/main/resources directory. Update the following properties as needed:<br>
spring.data.mongodb.uri=mongodb+srv://<your_root>:<password>@<Link_for_your_cluster_application_connection><br>
spring.data.mongodb.database=<your_collection_name><br>

### API Endpoints
The API exposes the following endpoints:

**GET /api/allPosts**: Get a list of resources.<br>
**GET /api/posts/{text}**: Get a resource by ID or any text.<br>
**POST /api/post**: Create a new resource.<br>
**PUT /api//update/{id}**: Update a resource by ID.<br>
**DELETE /api/delete/{id}**: Delete a resource by ID.<br>
Replace /api/resource with your specific resource name and customize the endpoints as needed.
