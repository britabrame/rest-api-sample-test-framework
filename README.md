## About
Sample API test framework using the open source Star Wars API. This project was developed to serve as a simple skeleton for new projects, or as a sandbox to try out API test framework features.

### Built with
* Java
* RestAssured
* TestNG
* Maven

## Writing tests
Tests are maintained in the [StarWarsApiTest](https://github.com/britabrame/rest-api-sample-test-framework/blob/master/src/test/java/tests/StarWarsApiTest.java) class. 

### Example test
Test cases can follow this basic pattern:
```
    @Test
    public void verifyPersonCount() {
        // 1. Create an instance of the StarWarsApi class
        StarWarsApi swapi = new StarWarsApi();
        
        // 2. Call the the desired request method and store
        // the rturned JsonPath object in a a variable
        JsonPath json = swapi.getPeople();
        
        // 3. Use the get() method to access data from the 
        // Json response of the request
        int actualCount = json.get("count");
        
        // 4. Perform asserts on the data
        Assert.assertEquals(actualCount, 82);
    }
```

### Asserts
The following Asserts have already been implemented in the `src/test/java/apis/StarWarsApi.java` class so that they run automatically whenever the methods of that class (such as `getPeople()` or `getFilms()`) are called in tests:
  * Assert status code of responses is 200
  * Assert response time is under a configured max time ( from `src/Configurations.properties`)
  * Assert content type

*Note: Since every test written with this framework will automatically assert a 200 status code, validating other response codes is not currently supported (such as testing an invalid case with a 500 response). This could be changed later by removing the status code assertion from the ResponseSpecification and implementing a status code parameter in the `StarWarsApi.java` methods.* 

## Creating new request methods
[StarWarsApi.java](https://github.com/britabrame/rest-api-sample-test-framework/tree/master/src/test/java) is a custom class that contains sample methods for making requests to SWAPI endpoints. This allows the user to write test scripts without rewriting common request code.

Currently, there are only 2 request methods included, but SWAPI has several other endpoints we can develop code to access, and there are multiple different ways we might want to send requests to each one. The following are guidelines for extending and maintaining the request code.
### Use RequestSpecification & ResponseSpecification
Whenever new methods are added to [StarWarsApi.java](https://github.com/britabrame/rest-api-sample-test-framework/tree/master/src/test/java) to support additional SWAPI request types, the RestAssured request code must include the RequestSpecification and the ResponseSpecification to ensure code maintainability.
* **RequestSpecification:** Allows you to specify common request details and reuse them.
*  **ResponseSpecification:** Allows you to specify how the expected response must look for a test to pass. 

Example:
```
    public JsonPath getPeople() {
        Response response = RestAssured.given()
                .spec(requestSpec) // Passing in the RequestSpecification
                .when()
                .get("/people/")
                .then()
                .spec(responseSpec) // Passing the ResponseSpecification
                .extract().response();

        return response.jsonPath();
    }
```
Checkout the existing `requestSpec` and `responseSpec` variables in the `StarWarsApi` constructor - if the existing specs are not appropriate for your new request, then create new ones and initialize them in the constructor.
## Configurations
[Configurations.properties](https://github.com/britabrame/rest-api-sample-test-framework/blob/master/src/Configurations.properties) allows the user to maintain global configurations. Currently only BASE_URL and MAX_RESPONSE_TIME are maintained. Create new configurations by updating `Configurations.properties`. Example for accessing configured values:
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
From the project home directory, run:
   ```
   mvn test
   ```

