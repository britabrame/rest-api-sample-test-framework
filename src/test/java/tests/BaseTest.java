package tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class BaseTest {

    @BeforeClass
    public void setup(){
        // Create properties file
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/Configurations.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set base uri
        RestAssured.baseURI = properties.getProperty("BASE_URL");
    }
}
