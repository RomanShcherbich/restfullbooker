package booker;

import com.google.gson.Gson;
import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthTest {

    public static final String CREDENTIALS = new Gson().toJson(new User("admin", "password123"));
//    """
//    {
//        "username" : "admin",
//        "password" : "password123"
//    }
//    """;

    @Test
    public void GetTokenTest() {
        String token = given()
                    .contentType("application/json")
                    .body(CREDENTIALS)
                .when()
                    .log().all()
                    .post("http://localhost:3001/auth")
                .then()
                    .log().all()
                    .statusCode(200)
                    .extract()
                    .body()
                    .path("token");
        assertThat(token, allOf(notNullValue(), hasLength(15)));
        Assert.assertNotNull(token);
    }

}
