package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends HerOkuAppBaseUrl {
    /*
      Given
          https://restful-booker.herokuapp.com/booking/2279
      When
          I send GET Request to the url
      Then
          Response body should be like that;
           {
            "firstname": "John",
            "lastname": "Smith",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2018-01-01",
                "checkout": "2019-01-01"
            },
            "additionalneeds": "Breakfast"
            }
   */
    @Test
    public void get09() {
//        Set the URL
        spec.pathParams("first", "booking", "second", 794);

//        Set the expected data
        HerOkuAppTestData obj1 = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = obj1.bookingMapsMethod("2018-01-01", "2019-01-01");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "John");
        expectedData.put("lastname", "Smith");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingDatesMap);
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println("Expected data: " + expectedData);

//        Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

//        Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));

        assertEquals(expectedData.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedData.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
    }

    @Test
    public void get09b() {// Dynamic yontem
//        Set the URL
        spec.pathParams("first", "booking", "second", 794);

//        Set the expected data
        HerOkuAppTestData obj1 = new HerOkuAppTestData();
        Map<String, String> bookingDatesMap = obj1.bookingMapsMethod("2018-01-01", "2019-01-01");

        Map<String, Object> expectedData = obj1.expectedDataMethod("John", "Smith", 111, true, bookingDatesMap, "Dinner");

        System.out.println("Expected data: " + expectedData);

//        Send the request and get the response
        Response response = given().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

//        Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("Actual data: " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(expectedData.get("firstname"), actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"), actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"), actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), actualData.get("depositpaid"));

        assertEquals(expectedData.get("checkin"), ((Map)actualData.get("bookingdates")).get("checkin"));
        assertEquals(expectedData.get("checkout"), ((Map)actualData.get("bookingdates")).get("checkout"));
        assertEquals(expectedData.get("additionalneeds"), actualData.get("additionalneeds"));
    }
}