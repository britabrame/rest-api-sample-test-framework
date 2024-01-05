package apis;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class is used to maintain methods for accessing SWAPI endpoints/resources.
 */
public class StarWarsApi {
    Properties properties;
    ResponseSpecification responseSpec;
    RequestSpecification requestSpec;

    public StarWarsApi(){
        // Create properties file
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/Configurations.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create request specification for common request parameters
        requestSpec = new RequestSpecBuilder()
                .setBaseUri(properties.getProperty("BASE_URL"))
                .build();

        // Create response specification for common assertions
        long maxResponseTime = Long.parseLong(properties.getProperty("MAX_RESPONSE_TIME"));
        responseSpec = (ResponseSpecification) new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType("application/json")
                .expectResponseTime(Matchers.lessThan(maxResponseTime))
                .build();
    }

    /**
     * Send a GET request to /people/ endpoint.
     * The returned JsonPath object can be used to access data from the JSON response.
     * @return JsonPath
     */
    public JsonPath getPeople() {
        Response response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/people/")
                .then()
                .spec(responseSpec)
                .extract().response();

        return response.jsonPath();
    }

    /**
     * Send a GET request to /films/ endpoint.
     * The returned JsonPath object can be used to access data from the JSON response.
     * @return JsonPath
     */
    public JsonPath getFilms() {
        Response response = RestAssured.given()
                .spec(requestSpec)
                .when()
                .get("/films/")
                .then()
                .spec(responseSpec)
                .extract().response();

        return response.jsonPath();
    }
}
