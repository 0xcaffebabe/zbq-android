package wang.ismy.zbq.login;

import android.util.Log;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import wang.ismy.zbq.R;
import wang.ismy.zbq.app.App;
import wang.ismy.zbq.app.ZbqResponse;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.util.DigestUtil;
import wang.ismy.zbq.util.StringUtil;
import wang.ismy.zbq.util.SystemUtil;

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


        if (StringUtil.isEmpty(userModel.getUsername())){
            SystemUtil.error(R.string.username_not_null);
        }

        if (StringUtil.isEmpty(userModel.getPassword())){
            SystemUtil.error(R.string.password_not_null);
        }

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
