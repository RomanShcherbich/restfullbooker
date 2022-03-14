package restassuredtests;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    @Test
    public void GetTokenTest() {
        assertThat(getToken(), allOf(notNullValue(), hasLength(15)));
        Assert.assertNotNull(getToken());
    }

}
