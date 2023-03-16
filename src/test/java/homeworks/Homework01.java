package homeworks;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class Homework01 extends HerOkuAppBaseUrl {
    /*
    1)
        Given
            https://restful-booker.herokuapp.com/booking?firstname=Almedin&lastname=Alikadic
        When
            User sends get request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Almedin" and lastname is "Alikadic"
*/
    @Test
    public void homework01() {
//    i)       Set the URL
        spec.pathParam("first", "booking").
                queryParams("firstname", "Almedin",
                        "lastname", "Alikadic");

//    ii)      Set the expected data

//    iii)     Send the request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();

//    iv)     Do Assertion
        response.then().statusCode(200);
        assertTrue(response.asString().contains("bookingid"));

    }
    /*
     2)
        Given
          https://reqres.in/api/unknown/3
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json; charset=utf-8”
        And
            Response body should be like;(Soft Assertion)
        {
        "data": {
            "id": 3,
            "name": "true red",
            "year": 2002,
            "color": "#BF1932",
            "pantone_value": "19-1664"
        },
        "support": {
            "url": "https://reqres.in/#support-heading",
            "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
        }
}
      */
}
