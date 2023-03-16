package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class ReqresInBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setSpec() {
        spec = new RequestSpecBuilder().setAccept(ContentType.JSON).setBaseUri("https://reqres.in").build();
    }

}
