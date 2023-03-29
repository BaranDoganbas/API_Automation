package gmibank;

import base_urls.GmiBankBaseUrl;
import gmibank.pojos.Country;
import gmibank.pojos.States;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PostCountry extends GmiBankBaseUrl {

    /*
        Given
            https://gmibank.com/api/tp-countries
        And
              {
              "name": "Banana Republic",
              "states": [
                {
                  "id": 1,
                  "name": "Apple"
                },
                {
                  "id": 2,
                  "name": "Orange"
                },
                {
                  "id": 3,
                  "name": "Pear"
                }
              ]
            }
        When
            User sends POST Request to the URL
        Then
            Status Code should be 201
        And
            Body should be like: {
                                "id": 177538,
                                "name": "Banana Republic",
                                "states": [
                                    {
                                        "id": 1,
                                        "name": "Apple",
                                        "tpcountry": null
                                    },
                                    {
                                        "id": 2,
                                        "name": "Orange",
                                        "tpcountry": null
                                    },
                                    {
                                        "id": 3,
                                        "name": "Pear",
                                        "tpcountry": null
                                    }
                                ]
                            }
     */

    @Test
    public void postCountry() {
//        Set the URL
        spec.pathParams("first","api","second", "tp-countries");

//        Set the expected data
        States state1 = new States(1, "Apple", null);
        States state2 = new States(2, "Orange", null);
        States state3 = new States(3, "Pear", null);

        List<States> stateList = new ArrayList<>();
        stateList.add(state1);
        stateList.add(state2);
        stateList.add(state3);

        Country expectedData = new Country("Banana Republic", stateList);
        System.out.println(expectedData);

//        Send the request and get the response
        Response response = given(spec).body(expectedData).when().post("{first}/{second}");
        response.prettyPrint();

//        Do Assertion
        Country actualData = ObjectMapperUtils.convertJsonToJava(response.asString(), Country.class);
        System.out.println(actualData);

        assertEquals(expectedData.getName(), actualData.getName());
        assertEquals(state1.getName(), actualData.getStates().get(0).getName());
        assertEquals(state1.getId(), actualData.getStates().get(0).getId());
        assertEquals(state2.getName(), actualData.getStates().get(1).getName());
        assertEquals(state2.getId(), actualData.getStates().get(1).getId());
        assertEquals(state3.getName(), actualData.getStates().get(2).getName());
        assertEquals(state3.getId(), actualData.getStates().get(2).getId());

    }
}
