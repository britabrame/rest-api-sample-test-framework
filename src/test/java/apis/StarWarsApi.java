package apis;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.Validator;

/**
 * This class is used to maintain methods for accessing SWAPI endpoints/resources.
 */
public class StarWarsApi {
    Validator validator;
    public StarWarsApi(){
        validator = new Validator();
    }

    /**
     * Send a GET request to /people/ endpoint.
     * The returned JsonPath object can be used to access data from the JSON response.
     * @return JsonPath
     */
    public JsonPath getPeople() {
        Response response = RestAssured.given()
                .when()
                .get("/people/")
                .then().extract().response();

        validator.validate(response);

        return response.jsonPath();
    }

    /**
     * Send a GET request to /films/ endpoint.
     * The returned JsonPath object can be used to access data from the JSON response.
     * @return JsonPath
     */
    public JsonPath getFilms() {
        Response response = RestAssured.given()
                .when()
                .get("/films/")
                .then().extract().response();

        validator.validate(response);

        return response.jsonPath();
    }
}
