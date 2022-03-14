package localbooker;

import io.restassured.http.ContentType;
import model.Booking;
import org.testng.annotations.Test;

import static constants.Endpoints.BOOKING_URN;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateBookingTest extends BaseTest {

    public static final String BOOKING_PATH = "booking";

    @Test
    public void createBookingTest() {
        Booking createdBooking = given()
                .spec(requestSpec)
                .body(new Booking())
        .when()
                .log().body()
                .post(BOOKING_URN)
        .then()
                .log().body()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject(BOOKING_PATH, Booking.class);
        assertThat(createdBooking, equalTo(new Booking()));
    }

}
