import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;

public class DeleteMethods {


    @Test
    public void deletePost() {
        when()
                .delete("http://localhost:3000/posts/14")
        .then()
                .log()
                .all()
                .statusCode(Matchers.equalTo(200));
    }
}
