package gmibank;

import base_urls.GmiBankBaseUrl;
import gmibank.pojos.Account;
import gmibank.pojos.Customer;
import gmibank.pojos.User;
import io.restassured.response.Response;
import org.junit.Test;
import util.ObjectMapperUtils;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetCustomer extends GmiBankBaseUrl {
       /*
    Given
        https://www.gmibank.com/api/tp-customers/133986
    When
        User sends Get request
    Then
        Status code should be 200
     And
        Response body should be like:
{
    "id": 133986,
    "firstName": "Danika",
    "lastName": "Huel",
    "middleInitial": "S",
    "email": "danikahuel@gmail.com",
    "mobilePhoneNumber": "155-489-7844",
    "phoneNumber": "155-489-7844",
    "zipCode": "32476",
    "address": "3848 Lang Hill",
    "city": "Hsajkhfja",
    "ssn": "725-97-6213",
    "createDate": "2022-01-21T05:00:00Z",
    "zelleEnrolled": false,
    "country": null,
    "state": "",
    "user": {
        "id": 134701,
        "login": "raymundo.moen",
        "firstName": "Danika",
        "lastName": "Huel",
        "email": "danikahuel@gmail.com",
        "activated": true,
        "langKey": "en",
        "imageUrl": null,
        "resetDate": null
    },
    "accounts": [
        {
            "id": 128481,
            "description": "Description",
            "balance": 0,
            "accountType": "CHECKING",
            "accountStatusType": "ACTIVE",
            "createDate": "2022-01-04T21:00:00Z",
            "closedDate": "2022-01-04T21:00:00Z",
            "employee": null,
            "accountlogs": null
        },
        {
            "id": 131776,
            "description": "mfy",
            "balance": 536846,
            "accountType": "CREDIT_CARD",
            "accountStatusType": "ACTIVE",
            "createDate": "2022-01-18T21:00:00Z",
            "closedDate": "2022-01-18T21:00:00Z",
            "employee": null,
            "accountlogs": null
        }
    ]
}
     */

    @Test
    public void gmiBankCustomer() {
//        i)   Set the URL
        spec.pathParams("first", "api", "second", "tp-customers", "third", 133986);

//        ii)  Set the expected data
        //Country country = new Country("Turkey", null);
        User user = new User(134701, "raymundo.moen", "Danika", "Huel", "danikahuel@gmail.com", true, "en", null, null);
        Account account1 = new Account(128481, "Description", 0, "CHECKING", "ACTIVE", "2022-01-04T21:00:00Z", "2022-01-04T21:00:00Z", null, null);
        Account account2 = new Account(131776, "mfy", 536846, "CREDIT_CARD", "ACTIVE", "2022-01-18T21:00:00Z", "2022-01-18T21:00:00Z", null, null);
        ArrayList<Account> accountList = new ArrayList<>();
        accountList.add(account1);
        accountList.add(account2);

        Customer expectedData = new Customer(133986, "Danika", "Huel", "S", "danikahuel@gmail.com", "155-489-7844", "155-489-7844", "32476", "3848 Lang Hill", "Hsajkhfja", "725-97-6213", "2022-01-21T05:00:00Z", false, null, "", user, accountList);
        System.out.println("expectedData = " + expectedData);

//        iii) Send the request and get the response
        Response response = given(spec).get("{first}/{second}/{third}");
        response.prettyPrint();

//        iv)  Do assertion
        Customer actualData = ObjectMapperUtils.convertJsonToJava(response.asString(),Customer.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getCity(),actualData.getCity());
        assertEquals(expectedData.getMiddleInitial(), actualData.getMiddleInitial());
        assertEquals(expectedData.getEmail(), actualData.getEmail());
        assertEquals(expectedData.getMobilePhoneNumber(), actualData.getMobilePhoneNumber());
        assertEquals(expectedData.getPhoneNumber(), actualData.getPhoneNumber());
        assertEquals(expectedData.getZipCode(), actualData.getZipCode());
        assertEquals(expectedData.getAddress(), actualData.getAddress());
        assertEquals(expectedData.getCity(), actualData.getCity());
        assertEquals(expectedData.getSsn(), actualData.getSsn());
        assertEquals(expectedData.getCreateDate(), actualData.getCreateDate());
        assertEquals(expectedData.isZelleEnrolled(), actualData.isZelleEnrolled());
        assertEquals(expectedData.getCountry(), actualData.getCountry());
        assertEquals(expectedData.getState(), actualData.getState());

        assertEquals(user.getId(),actualData.getUser().getId());
        assertEquals(user.getLogin(),actualData.getUser().getLogin());
        assertEquals(user.getFirstName(),actualData.getUser().getFirstName());
        assertEquals(user.getLastName(),actualData.getUser().getLastName());
        assertEquals(user.getEmail(),actualData.getUser().getEmail());
        assertEquals(user.isActivated(),actualData.getUser().isActivated());
        assertEquals(user.getLangKey(),actualData.getUser().getLangKey());
        assertEquals(user.getImageUrl(),actualData.getUser().getImageUrl());
        assertEquals(user.getResetDate(),actualData.getUser().getResetDate());

        assertEquals(account1.getId(),actualData.getAccounts().get(0).getId());
        assertEquals(account1.getDescription(),actualData.getAccounts().get(0).getDescription());
        assertEquals(account1.getBalance(),actualData.getAccounts().get(0).getBalance());
        assertEquals(account1.getAccountType(),actualData.getAccounts().get(0).getAccountType());
        assertEquals(account1.getAccountStatusType(),actualData.getAccounts().get(0).getAccountStatusType());
        assertEquals(account1.getCreateDate(),actualData.getAccounts().get(0).getCreateDate());
        assertEquals(account1.getClosedDate(),actualData.getAccounts().get(0).getClosedDate());
        assertEquals(account1.getEmployee(),actualData.getAccounts().get(0).getEmployee());
        assertEquals(account1.getAccountlogs(),actualData.getAccounts().get(0).getAccountlogs());

        assertEquals(account2.getId(),actualData.getAccounts().get(1).getId());
        assertEquals(account2.getDescription(),actualData.getAccounts().get(1).getDescription());
        assertEquals(account2.getBalance(),actualData.getAccounts().get(1).getBalance());
        assertEquals(account2.getAccountType(),actualData.getAccounts().get(1).getAccountType());
        assertEquals(account2.getAccountStatusType(),actualData.getAccounts().get(1).getAccountStatusType());
        assertEquals(account2.getCreateDate(),actualData.getAccounts().get(1).getCreateDate());
        assertEquals(account2.getClosedDate(),actualData.getAccounts().get(1).getClosedDate());
        assertEquals(account2.getEmployee(),actualData.getAccounts().get(1).getEmployee());
        assertEquals(account2.getAccountlogs(),actualData.getAccounts().get(1).getAccountlogs());
    }
}