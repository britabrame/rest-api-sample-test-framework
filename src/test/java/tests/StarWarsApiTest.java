package tests;

import apis.StarWarsApi;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Main test scripts file.
 */
public class StarWarsApiTest extends BaseTest {

    @Test
    public void verifyPersonCount() {
        StarWarsApi swapi = new StarWarsApi();
        JsonPath json = swapi.getPeople();
        Assert.assertEquals((Integer) json.get("count"), 82);
    }

    @Test
    public void verifyFilmCount(){
        StarWarsApi swapi = new StarWarsApi();
        JsonPath json = swapi.getFilms();
        Assert.assertEquals((Integer) json.get("count"), 6);
    }

    @Test
    public void verifyFilmDetails(){
        StarWarsApi swapi = new StarWarsApi();
        JsonPath json = swapi.getFilms();
        Assert.assertEquals(json.get("results[0].title"), "A New Hope");
        Assert.assertEquals((Integer) json.get("results[0].episode_id"), 4);
        Assert.assertEquals(json.get("results[0].director"), "George Lucas");
        Assert.assertEquals(json.get("results[0].producer"), "Gary Kurtz, Rick McCallum");
        Assert.assertEquals(json.get("results[0].release_date"), "1977-05-25");
    }
}
