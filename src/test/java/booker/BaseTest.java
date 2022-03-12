package booker;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Booking;
import model.BookingDates;
import model.User;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public static final String CREDENTIALS = new Gson().toJson(new User("admin", "password123"));
    public static final Booking TEST_BOOKING = initTestBookingEntity();
    public static final String TEST_BOOKING_JSON = new Gson().toJson(TEST_BOOKING);
    private String userToken = null;

    public String getToken() {
        if (userToken == null) {
            userToken = given()
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
        }
        return userToken;
    }

    public ValidatableResponse createBookingRequest() {
        return given()
                .contentType(ContentType.JSON)
                .body(TEST_BOOKING_JSON)
                .when()
                .post("http://localhost:3001/booking")
                .then()
                .statusCode(200);
    }

    public int createTestBookingAndGetId() {
        return createBookingRequest()
                .extract()
                .body()
                .path("bookingid");
    }

    public static Booking initTestBookingEntity() {
        return Booking.builder()
                .firstname("TestFirstname")
                .lastname("TestLastname")
                .totalPrice(777)
                .depositPaid(true)
                .bookingDates(new BookingDates())
                .additionalNeeds("Hookah")
                .build();
    }

}
