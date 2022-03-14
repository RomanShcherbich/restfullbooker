package adapter;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import model.User;

import java.io.FileReader;

public class AuthAdapter extends BaseAdapter {

    public static final String AUTH = "auth";

    public String getToken(User user) {
        return post(AUTH, new Gson().toJson(user))
                .body()
                .path("token");
    }

    @SneakyThrows
    public String getAdminToken() {
        User admin = new Gson().fromJson(
                new FileReader("src/test/resources/AdminCredentials.json"), User.class
        );
        return getToken(admin);
    }

}
