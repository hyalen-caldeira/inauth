Hi Charles, follow this instructions to test the application. Please, let me know if you have any question and thanks for the opportunity to do the assessment.
=========
### Considerations ###
  * The application is configured to run on port 8088
  * The application use an in memory DB, H2 Database
  * The application was developed using Spring Boot. This is a stand alone application where everything you need is embedded on it (Tomcat, DB, ...)
  * The application will populate the DB with 10000 records previously generated
    * https://github.com/hyalen-moreira/inauth/blob/master/src/main/java/us/hyalen/inauth/util/Utils.java
  * I'm using the Google Maps API to get information from given coordinates
    * https://github.com/hyalen-moreira/inauth/tree/master/src/main/java/us/hyalen/inauth/connection
  * In case you prefer to clone the repository, build and run by yourself, instead of execute the .jar file, you will need a Google Maps API key
    * To get a Google Maps API key
      * https://developers.google.com/maps/documentation/geocoding/get-api-key
    * To set a key in the app
      * https://github.com/hyalen-moreira/inauth/blob/master/src/main/java/us/hyalen/inauth/connection/GoogleMapsApi.java
### To execute the app ###
* `java -jar inauth-0.0.1-SNAPSHOT.jar`
* I've sent the .jar file to your email
### To see all the code ###
Clone the GitHub project https://github.com/hyalen-moreira/inauth.git
### To see the seed data ###
https://github.com/hyalen-moreira/inauth/blob/master/src/main/resources/data-h2.sql
### To test the endpoints ###
Once the application is running, open a browser of your choice and:
  * To get all coordinates
    * http://localhost:8088/inauth/api/locations
  * To get data from a specific coordinate
    * http://localhost:8088/inauth/api/locations/40.714224,-73.961452
    * If the coordinate doens't exist in the DB, the app will return `404 - No Found`
    * Otherwise the application will access a Google Maps API and will return information about the coordinate
  * To add a new coordinate
    * http://localhost:8088/inauth/api/locations
    * Inform the latitude and longitude on body of the http post request
    * `{"latitude" : "42.714224", "longitude" : "-53.961452"}`
### To see a report about distances (spreadsheet as requested) ###
  * http://localhost:8088/inauth/api/assessment/40.714224,-73.961452
  * If the coordinates is within USA you will see the message: `The given coordinate is withing the USA`
  * Otherwise you will see a report about distances of pre-defined cities.
  * Accessing the http://localhost:8088/inauth/api/assessment/19.42847,-99.12766 endpoint you will see:
    ```
    The given coordinate has: 

    7020.238077159492 MILES of distance from TOKYO
    2643.954095668077 MILES of distance from LIMA
    8616.364079471108 MILES of distance from RIYADH
    4633.421762585401 MILES of distance from REYKJAVIK
    8061.066959776479 MILES of distance from SYDNEY
    6016.952416298471 MILES of distance from ZURICH
    0.0 MILES of distance from MEXICO and it is also within 500 MILES of distance of this city.
    ```
  * To access the in memory database
    * http://localhost:8088/h2-console and then inform:
      * Driver Class: org.h2.Driver
      * JDBC URL: jdbc:h2:mem:inauth
      * Login: inauth
      * Password: inauth
    * Press the `connect` button

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
I've been using the H2 in memory DB. From H2 Database console, I run the following command to export the DB to be used as seed data:
```
CALL CSVWRITE(
    '/Users/hyalencaldeira/Documents/workspace/inauth/MyCSV.txt', 
    'SELECT CONCAT(
        CHAR(40), 
        LATITUDE, 
        CHAR(44), 
        LONGITUDE, 
        CHAR(41)) 
    FROM 
        LOCATION', 
    'charset=UTF-8 fieldSeparator=' || CHAR(9)
);
```
### Task 2) ###
I've used spring-boot to make the server up, runnable and ready to receive HTTP requests. The server is started on port 8088.

### Task 3) ###
Besides the Micro Service to fetch coordinates information, I've also added solution performing operations through coordinates of some locations:

#### Create 3 Web Service: ####
 * `getAllDataSets` - GET method to return all data in the DB.
 * `getData(latitude, longitude)` - GET method to return if the coordinates exists in the DB.
 * `addData(latitude, longitude)` - POST method to add coordinate to the DB.
 * Apart of the above web services, I've also added the `getAssessment(latitude, longitude)` service - GET method to return a distance's report.

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
   * You may see an example of this requirement accessing the http://localhost:8088/inauth/api/assessment/19.42847,-99.12766 endpoint

### Task 4) ###
 * Missing - I've added unit tests for all the existing classes in the workspace.

### Task 5) ###
 * Missing - I've added **Spring Integration Tests** for all the `API endpoints`.

Existing API Endpoints
======================
1) List Locations:
   **[GET]** `http://localhost:8088/api/locations`

2) Fetch Location Details:
   **[GET]** `http://localhost:8088/api/locations/{latitude,longitude}`

3) Add Location:
   **[POST]** `http://localhost:8088/api/locations`

4) Fetch Report Details:
   **[GET]** `http://localhost:8088/api/assessment/{latitude,longitude}`

Technical Information
=====================
 * To test, you should have Java 8, Maven and Git installed.
 * The sample data has been pre-loaded so that the `endpoints` can be tested. Please refer to `data-h2.sql`.

Tech Stack
==========
 * Google Maps API
 * Java 8.x
 * Maven 3.x
 * Spring Framework 4.x
 * Spring Boot 1.5.6
 * Google Maps API
 * Hibernate
 * JPA
 * H2 database
 * JUnit 4.x
 * Hamcrest
 * Spring Integration Tests
 * Project Lombok
