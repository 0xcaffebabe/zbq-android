package wang.ismy.zbq.person;

import android.app.Person;
import android.util.Log;

import com.google.gson.Gson;

import java.net.URI;

import wang.ismy.zbq.app.App;
import wang.ismy.zbq.app.ZbqResponse;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.login.LoginApp;

public class PersonApp extends App {

    private static final PersonApp instance = new PersonApp();

    private PersonApp() {
    }

    public UserInfo getSelfUserInfo() throws Throwable {

        ZbqResponse response = get(URL.GET_SELF_USER_INFO_URL);

        if (response.getResult().isSuccess()){
            Log.e("iii",response.getResult().getData().toString());
            UserInfo userInfo = new Gson().fromJson(response.getResult().getData(),UserInfo.class);
            if (userInfo.getProfile().startsWith("/img")){
                userInfo.setProfile(URL.BASE_URL+"img/anonymous.jpg");
            }else{
                userInfo.setProfile("https:"+userInfo.getProfile());
            }

            return userInfo;
        }else{
            throw new AppException(response.getResult().getMsg());
        }
    }

    public static PersonApp newInstance() {
        return instance;
    }
}
