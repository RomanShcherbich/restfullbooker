package booker;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import model.Booking;
import model.BookingDates;
import model.User;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class UpdateBookingTest extends BaseTest {

    private Booking updatedBooking = null;

    @Test
    public void updateBookingTest() {
        int testBookingId = createTestBookingAndGetId();
        Booking actualBooking = given()
                .spec(requestSpec)
                .cookie("token", getToken())
                .body(getUpdatedBookingJson())
        .when()
                .log().all()
                .put(StringUtils.join(BOOKING_ENDPOINT, testBookingId))
        .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Booking.class);
        assertThat(actualBooking, equalTo(getUpdatedBooking()));
    }

    private Booking getUpdatedBooking() {
        if (updatedBooking == null) {
            updatedBooking = initTestBookingEntity();
            updatedBooking.setLastname("UpdatedTestLastname");
        }
        return updatedBooking;
    }

    private String getUpdatedBookingJson() {
        return new Gson().toJson(getUpdatedBooking());
    }

}
