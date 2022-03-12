package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDates  {

    @SerializedName("checkin")
    @Expose
    @Builder.Default
    private String checkin = "2019-12-31";
    @SerializedName("checkout")
    @Expose
    @Builder.Default
    private String checkout = "2021-02-23";

}
