package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get02 {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/0
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */
    @Test
    public void get02() {

//        1st Step: Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/0";

//        2nd Step: Set the expected data ==> (Post, Put, Patch)

//        3rd Step: Send the request, get the response
        Response response = given().when().get(url);
        response.prettyPrint();



    }

}