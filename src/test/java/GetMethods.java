import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetMethods {

    public RequestSpecification req;
    public ResponseSpecification resp;

    @BeforeClass
    public void setUp() {

        req = new RequestSpecBuilder().setBaseUri("http://localhost:3000")
                .setBasePath("/posts")
                .setContentType(ContentType.JSON)
                .build();

        resp = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        RequestLoggingFilter regLog = new RequestLoggingFilter();
        ResponseLoggingFilter respLog = new ResponseLoggingFilter();
        RestAssured.filters(regLog, respLog);

    }

    @Test
    public void getPosts() {

        given()
                .spec(req)
        .when()
                .get()
        .then()
                .spec(resp)
                .assertThat()
                .body("title[2]", Matchers.equalTo("Post3"));

    }
}

