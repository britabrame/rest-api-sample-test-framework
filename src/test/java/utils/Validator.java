package utils;

import io.restassured.response.Response;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** Helper class to take care of common Asserts on Responses. */
public class Validator {
    Properties properties;

    public Validator(){
        // Create properties file
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/Configurations.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void validate(Response response){
        // Assert successful status code
        Assert.assertEquals(response.getStatusCode(), 200);

        // Assert the response time is under max
        long responseTime = response.getTime();
        long maxResponseTime = Long.parseLong(properties.getProperty("MAX_RESPONSE_TIME"));
        int compare = Long.compare(responseTime, maxResponseTime);
        Assert.assertTrue(compare < 0 , "Response time should be less than " + maxResponseTime + "ms");
    }
}
