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
    public String firstname;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("totalprice")
    @Expose
    public Integer totalPrice;
    @SerializedName("depositpaid")
    @Expose
    public Boolean depositPaid;
    @SerializedName("bookingdates")
    @Expose
    public BookingDates bookingDates;
    @SerializedName("additionalneeds")
    @Expose
    public String additionalNeeds;

}