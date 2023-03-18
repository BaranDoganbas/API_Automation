package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kannan Ahluwalia", "The Hon. Tara Chaturvedi" and "Damayanti Dubashi" are among the users
        And
            The female users are less than or equals to male users
            (Kadın kullanıcı sayısı erkek kullanıcı sayısından küçük yada eşit olamlı)
     */

    @Test
    public void get11() {
//        i)   Set the URL
        spec.pathParams("first", "users");

//        ii)  Set the expected data

//        iii) Send the request and get the response
        Response response = given().spec(spec).get("{first}");
        response.prettyPrint();

//        iv)  Do assertion
        response.then().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Gov. Vrinda Panicker", "Rev. Jay Shukla", "Mandaakin Bhattacharya"));

//        Kadin ve erkek kullanici sayilarini karsilastiralim
//        1. Path:
        JsonPath jsonPath = response.jsonPath();
        List<String> genders = response.jsonPath().getList("data.gender");
        System.out.println("Genders: " + genders);

        int femaleNumber = (int) genders.stream().filter(gender -> gender.equals("female")).count();

        System.out.println("Kadin sayisi: " + femaleNumber);
        assertTrue(femaleNumber <= genders.size() - femaleNumber);

//        2. Path: Kadin ve erkek kullanici sayilarini karsilastiralim
        List<String> femaleUserList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleUserList);
        List<String> maleUserList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleUserList);

        assertTrue(femaleUserList.size() <= maleUserList.size());
    }




}


