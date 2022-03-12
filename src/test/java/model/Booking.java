package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Booking implements JsonModel {

    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("totalprice")
    @Expose
    private Integer totalPrice;
    @SerializedName("depositpaid")
    @Expose
    private Boolean depositPaid;
    @SerializedName("bookingdates")
    @Expose
    private BookingDates bookingDates;
    @SerializedName("additionalneeds")
    @Expose
    private String additionalNeeds;

}