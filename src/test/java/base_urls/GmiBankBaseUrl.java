package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GmiBankBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setSpec() {
        spec = new RequestSpecBuilder().setBaseUri("https://www.gmibank.com").setContentType(ContentType.JSON).build();
    }
}
