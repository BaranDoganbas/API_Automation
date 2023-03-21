package put_requests;

import base_urls.DummyRestApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyRestApiDataPojo;
import pojos.DummyRestApiResponseBodyPojo;
import util.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyRestApiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
    @Test
    public void put02() {
//        Set the URL
        spec.pathParams("first", "update", "second", 21);

//        Set the expected data
        DummyRestApiDataPojo expectedData = new DummyRestApiDataPojo("Ali Can", 111111, 23, "Perfect image");
        System.out.println("expectedData = " + expectedData);//Update icin gonderilecek data
        DummyRestApiResponseBodyPojo responseBodyPojo = new DummyRestApiResponseBodyPojo("success", expectedData, "Successfully! Record has been updated.");

//        Send the request and get the response
        Response response = given().spec(spec).when().body(expectedData).put("/{first}/{second}");
        response.prettyPrint();

//        Do Assertion
        DummyRestApiResponseBodyPojo actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), DummyRestApiResponseBodyPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.getStatusCode());
        assertEquals(responseBodyPojo.getStatus(), actualData.getStatus());
        assertEquals(responseBodyPojo.getMessage(), actualData.getMessage());

        assertEquals(responseBodyPojo.getData().getEmployee_name(), actualData.getData().getEmployee_name());
        assertEquals(responseBodyPojo.getData().getEmployee_salary(), actualData.getData().getEmployee_salary());
        assertEquals(responseBodyPojo.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(responseBodyPojo.getData().getProfile_image(), actualData.getData().getProfile_image());




    }


}
