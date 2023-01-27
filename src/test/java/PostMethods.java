import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostMethods {

    public RequestSpecification req;
    public ResponseSpecification resp;

    @BeforeClass
    public void setUp() {

        req = new RequestSpecBuilder().setBaseUri("http://localhost:3000")
                .setBasePath("/posts")
                .setContentType(ContentType.JSON)
                .build();

        resp = new ResponseSpecBuilder().expectStatusCode(201)
                .expectContentType(ContentType.JSON)
                .build();

        RequestLoggingFilter regLog = new RequestLoggingFilter();
        ResponseLoggingFilter respLog = new ResponseLoggingFilter();
        RestAssured.filters(regLog, respLog);

    }

    @Test
    public void addPost() {
        Map<String, Object> addFromMap = new HashMap<>();
        addFromMap.put("title", "Post14");
        addFromMap.put("author", "Admin14");

        given()
                .spec(req)
        .when()
                .body(addFromMap)
                .post()
        .then()
                .spec(resp);

    }
}
