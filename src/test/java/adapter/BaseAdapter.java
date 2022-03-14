package adapter;

import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Booking;

import static io.restassured.RestAssured.given;

public abstract class BaseAdapter {

    public static final String BASE_URL = "http://localhost:3001/";

    protected RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setBaseUri(BASE_URL)
            .build();

    protected Response get(String url) {
        return given()
                .spec(requestSpec)
                .when()
                .get(url);
    }

    protected Response post(String url, String payload) {
        return given()
                .spec(requestSpec)
                .body(payload)
                .when()
                .post(url);
    }

}
