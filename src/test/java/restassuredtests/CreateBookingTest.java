package restassuredtests;

import model.Booking;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTest extends BaseTest {

    @Test
    public void createBookingTest() {
        var response = createBookingRequest();
        Booking actualBooking = response.extract()
                .body()
                .jsonPath()
                .getObject("booking", Booking.class);
        assertThat(actualBooking, equalTo(TEST_BOOKING));
    }

}
