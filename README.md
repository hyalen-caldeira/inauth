# Java - Reverse Geocoding - Google Maps API - Spring Boot - Rest API - Unit/Integration Test #

Objective
=========
Create a DB containing 10.000 random entries for valid latitude and longitude coordinates and performing some operations using the DB.

Evaluation areas
=========
The following areas were addressed: 
Java, Configuration, Architecture, Logic, Exception Handling, Logs, Spring, Persistence, REST, Tests, Documentation and Geocoding.

Requirements
============

### Task 1) ###
I've been using the H2 in memory DB. To populate it I 

### Task 2) ###
I've been using spring-boot to make the server up, runnable and ready to receive HTTP requests. The server is started on port 8088.

### Task 3) ###
Besides the Micro Service to fetch coordinates information, I've also added solution performing operations through coordinates of some locations:

#### Create 3 Web Service: ####
 * `getAllDataSets` - GET method to return all data in the DB.
 * `getData(latitude, longitude)` - GET method to return if the coordinates exists in the DB.
 * `addData(latitude, longitude)` - POST method to add coordinate to the DB.

#### From above Web Services, create a Java solution for: ####
 * Given the entry's coordinates, determine if those coordinates are within the United States.
 * If they're not within the United States, determine if the coordinates are within 500 miles of the following cities
   * Tokyo, Japan
   * Sydney, Australia
   * Riyadh, Saudi Arabia
   * Zurich, Switzerland
   * Reykjavik, Iceland
   * Mexico City, Mexico
   * Lima, Peru
 * For each of the above, tell how far away the entry's coordinates are from each city

#### Criteria 3: Filter all the orders having more that two products in the transaction. ####
```json
 eg:
 {
         "orderNumber": "RTL_1003",
         "discount": 19.99,
         "taxPercent": 8.5,
         "total": 139.97,
         "totalTax": 11.89,
         "grandTotal": 131.87,
         "status": "SHIPPED",
         "products": [
             {
                 "upc": "1358743283",
                 "sku": "7394650110003",
                 "description": "Polo Shirt",
                 "price": 19.99
             },
             {
                 "upc": "1458843283",
                 "sku": "7394750120000",
                 "description": "Floral Swing Skirt",
                 "price": 69.99
             },
             {
                 "upc": "1258793283",
                 "sku": "7394950140000",
                 "description": "True Skinny Jeans",
                 "price": 49.99
             }
         ]
     }
```

### Task 4) ###
 * I've added unit tests for all the existing classes in the workspace.

### Task 5) ###
 * I've added **Spring Integration Tests** for all the `API endpoints`.

Existing API Endpoints
======================
1) List Locations:
   **[GET]** `http://localhost:8088/api/locations`

2) Fetch Location Details:
   **[GET]** `http://localhost:8088/api/locations/{latitude,longitude}`

3) Add Location:
   **[POST]** `http://localhost:8088/api/locations`

Technical Information
=====================
 * To test, you should have Java 8, Maven and Git installed.
 * The sample data has been pre-loaded so that the `endpoints` can be tested. Please refer to `data-h2.sql`.

Tech Stack
==========
 * Java 8.x
 * Maven 3.x
 * Spring Framework 4.x
 * Spring Boot 1.5.6
 * Google Maps API
 * Hibernate
 * JPA
 * H2 database
 * JUnit 4.x
 * Mockito 2.x
 * Hamcrest
 * Spring Integration Tests
