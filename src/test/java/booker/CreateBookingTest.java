package booker;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import model.Booking;
import model.BookingDates;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

public class CreateBookingTest {

    private static final Booking BOOKING = Booking.builder()
            .firstname("TestFirstname")
            .lastname("TestLastname")
            .totalPrice(777)
            .depositPaid(true)
            .bookingDates(new BookingDates())
            .additionalNeeds("Hookah")
            .build();

    @Test
    public void createBookingTest() {
        var response = given()
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(BOOKING))
        .when()
                .post("http://localhost:3001/booking")
        .then()
                .statusCode(200);
        Booking actualBooking = response.extract()
                .body()
                .jsonPath()
                .getObject("booking", Booking.class);
        assertThat(actualBooking, equalTo(BOOKING));
//        int bookingId = response.extract()
//                .body()
//                .path("bookingid");
    }

}
