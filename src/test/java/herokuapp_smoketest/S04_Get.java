package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static herokuapp_smoketest.S01_Post.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class S04_Get extends HerOkuAppBaseUrl{

    /*
   Given
       https://restful-booker.herokuapp.com/booking/:id
   When
       User sends Get request
   Then
       Status code is 404
   And
       Response body is like "Not Found"
    */
    @Test
    public void get01(){//Silinen id'yi çağırarak 404 kodunu assert ederek nagatif test yapıyoruz.
        //Set the url
        spec.pathParams("first","booking","second",bookingId);

        //Set the expected data
        String expectedData = "Not Found";

        //Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(404,response.statusCode());
        assertEquals(expectedData,response.asString());
    }
}
