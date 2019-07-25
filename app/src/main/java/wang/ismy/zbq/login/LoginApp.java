package wang.ismy.zbq.login;

import android.renderscript.Script;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import wang.ismy.zbq.app.App;
import wang.ismy.zbq.app.ZbqResponse;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.model.Result;
import wang.ismy.zbq.util.DigestUtil;

public class LoginApp extends App {

    private static LoginApp instance = new LoginApp();

    public UserModel userModel = new UserModel();

    private LoginApp() {
        super();
    }

    public static LoginApp newInstance() {
        return instance;
    }

    public void login() throws Throwable {

        UserModel tmpModel = new UserModel(userModel.getUsername(), DigestUtil.md5(userModel.getPassword()));

        String json = JSON.toJson(tmpModel);

        Log.i("登录",json);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        ZbqResponse response = post(URL.LOGIN_URL, body);

        if (!response.getResult().isSuccess()){
            throw new AppException(response.getResult().getMsg());
        }
    }
}
