## About
Sample API test framework using the open source Star Wars API. This project was developed to serve as a simple skeleton for new projects, or as a sandbox to try out API test framework features.

### Built with
* Java
* RestAssured
* TestNG
* Maven

## Writing tests
Tests are maintained in the `src/test/java/tests/StarWarsApiTest.java` class. 

## Features
* **SWAPI interface**: `src/test/java/apis/StarWarsApi.java` class contains sample methods for sending requests against SWAPI endpoints. This interface allows the user to write test scripts without rewriting common request code. A more robust implementation of this project would have multiple class files in the api package, one for each API/endpoint as needed, and many methods for interacting with the endpoints. 
* **Built-in asserts**: Some assertions are done automatically by the `src/test/java/utils/Validator.java` class when the validate(response) method is called following a request. All methods invoking a request should include validate().
  * Assertions:
    * Assert response code is 200
      * A more comprehensive framework would permit assertions of other response codes as well
    * Assert that the response time is less than a configured max value
* **Configuration file**: `src/Configurations.properties` allows the user to maintain global configurations. Currently only BASE_URL and MAX_RESPONSE_TIME are maintained. Create new configurations by updating `Configurations.properties`. Example for accessing configured values:
    ```
      // Create properties file
      Properties properties = new Properties();
      try {
      properties.load(new FileInputStream("src/Configurations.properties"));
      } catch (IOException e) {
      e.printStackTrace();
      }
      String someConfigValue = properties.getProperty("SOME_CONFIG_KEY");
    ```
## Setup
1. Clone the project:
   ```
   git clone https://github.com/britabrame/rest-api-sample-test-framework.git
   ```
1. Install maven if it is not already installed locally

## Running tests
Run tests:
   ```
   mvn test
   ```

