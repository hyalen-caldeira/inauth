# Avenue Code Java Assessment #

Objective
=========
A fictional client has an existing Micro Service to fetch order and product information. Your job is to add a new `Search REST API` to display a list of potential matches. This exercise is expected to take around 3 to 4 hours of coding, but you are free to use as much time as you need.

Requirements
============
### Task 1) ###
The existing Micro Service to fetch order and product information has been provided for you. Your goal is to add a new generic `Search REST API` to retrieve all relevant information matching the following criteria:

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

### Task 2) ###
 * Add JUnit tests for all the existing classes in the workspace. Also, make sure to test-drive the new code being added.

### Task 3) ###
 * Add **Spring Integration Tests** for all the `API endpoints`.

### Note: You are free to refactor existing codebase to fulfil these requirements. ###

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
 * You should have Java 8, Maven and Git installed.
 * Execute `OrdersApplication.java` to start the server.
 * The server will be started on `8088` port.
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
 
Delivery Instructions
=====================

1) You must provide your BitBucket username. A free BitBucket account can be created at http://bitbucket.org

2) The recruiter will give you read permission to a repository named **java-assessment**, at https://bitbucket.org/ac-recruitment/java-assessment

3) You must fork this repository into a private repository on your own account and push your code in there.

4) Once finished, you must give the user **ac-recruitment** read permission on your repository so that you can be evaluated. Then, please contact back your recruiter and he will get an engineer to evaluate your test.

5) After you are evaluated, the recruiter will remove your read permission from the original repository.

Format
======

* This assessment must be delivered within 2 days.
* You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
    * How to compile and run the application.
    * How to run the suite of automated tests.
    * Mention anything that was asked but not delivered and why, and any additional comments.
* Any questions, please send an email to **recruitment.engineering@avenuecode.com**

Thank you,
The AvenueCode Recruiting Team