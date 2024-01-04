package apis;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.Validator;

public class StarWarsApi {
    Validator validator;
    public StarWarsApi(){
        validator = new Validator();
    }

    public JsonPath getPeople() {
        Response response = RestAssured.given()
                .when()
                .get("/people/")
                .then().extract().response();

        validator.validate(response);

        return response.jsonPath();
    }

    public JsonPath getFilms() {
        Response response = RestAssured.given()
                .when()
                .get("/films/")
                .then().extract().response();

        validator.validate(response);

        return response.jsonPath();
    }
}
