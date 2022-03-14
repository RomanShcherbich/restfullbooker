package bookingtests;

import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AuthTest extends BaseTest {

    @SneakyThrows
    @Test
    public void tokenReceivedTest() {
        assertThat(http.auth.getAdminToken(), allOf(notNullValue(), hasLength(15)));
    }

}
