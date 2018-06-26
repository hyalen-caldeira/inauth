# Java - Spring - Rest API - Unit/Integration Test #

Objective
=========
A fictional client has an existing Micro Service to fetch order and product information. This simple code contains a `Search REST API` to display a list of potential matches. 

Evaluation areas
=========
The following areas were addressed: 
Configuration, Architecture, Logic, Exception Handling, Logs, Spring, Persistence, REST, Tests, Documentation, and JAVA.

Requirements
============
### Task 1) ###
I've been using spring-boot to make the server up, runnable and ready to receive HTTP requests. The server should be started on port 8088.

### Task 2) ###
Besides the Micro Service to fetch order and product information, I've also added a new generic `Search REST API` to retrieve all relevant information matching the following criteria:

#### Criteria 1: Filter all the orders which are shipped. ####
 * This search criteria should be applied on the `status` column of the `orders` table.
 * The value of the `status` should be `SHIPPED`.

#### Criteria 2: Filter all the orders where discount has been applied. ####
 * This search criteria should be applied on the `discount` column of the `orders` table.

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

#### Criteria 4: Filter all the products whose price is more than $30. ####
 * This search criteria should be applied on the `price` column of the `products` table.

### Task 3) ###
 * I've added unit tests for all the existing classes in the workspace.

### Task 4) ###
 * I've added **Spring Integration Tests** for all the `API endpoints`.

Existing API Endpoints
======================
1) List Orders:
   **[GET]** `http://localhost:8088/orders`

2) Fetch Order Details:
   **[GET]** `http://localhost:8088/orders/{order_id}`

3) List Products:
   **[GET]** `http://localhost:8088/products`

4) Fetch Product Details:
   **[GET]** `http://localhost:8088/products/{product_id}`

Technical Information
=====================
 * To test, you should have Java 8, Maven and Git installed.
 * The sample data has been pre-loaded so that the new `Search API` can be tested. Please refer to `data-h2.sql`.

Tech Stack
==========
 * Java 8.x
 * Maven 3.x
 * Spring Framework 4.x
 * Spring Boot 1.5.6
 * Hibernate
 * JPA
 * H2 database
 * JUnit 4.x
 * Mockito 2.x
 * Hamcrest
 * Spring Integration Tests
