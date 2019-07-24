package wang.ismy.zbq.login;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import wang.ismy.zbq.app.App;
import wang.ismy.zbq.constant.URL;

public class LoginApp extends App {

    private static LoginApp instance = new LoginApp();

    public UserModel userModel = new UserModel();

    private LoginApp(){super();}

    public static LoginApp newInstance(){
        return instance;
    }

    public void login(){

        String json = JSON.toJson(userModel);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        try {
            Response response = post(URL.LOGIN_URL,body);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
