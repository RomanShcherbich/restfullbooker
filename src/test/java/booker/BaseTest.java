package booker;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import model.Booking;
import model.BookingDates;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BaseTest {

    public static final String BASE_URL = "http://localhost:3001/";
    public static final String BOOKING_ENDPOINT = "booking/";
    public static final Booking TEST_BOOKING = initTestBookingEntity();
    public static final String TEST_BOOKING_JSON = new Gson().toJson(TEST_BOOKING);
    private String userToken = null;

    RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_URL)
            .build();


    @SneakyThrows
    public String getToken() {
        if (userToken == null) {
            userToken = given()
                    .spec(requestSpec)
                    .body(new File("src/test/resources/AdminCredentials.json"))
                    .when()
                    .log().all()
                    .post("auth")
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
                .spec(requestSpec)
                .body(TEST_BOOKING_JSON)
                .when()
                .post("booking")
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
