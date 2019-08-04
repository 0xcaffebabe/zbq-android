package wang.ismy.zbq.login;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.databinding.ObservableBoolean;

import lombok.var;
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

    private Context context;

    public UserModel userModel = new UserModel();

    public ObservableBoolean remeberPassword = new ObservableBoolean();

    private LoginApp() {
        super();
    }

    public static LoginApp newInstance(Context context) {
        instance.context = context;
        SharedPreferences sharedPreferences = context.getSharedPreferences("zbq",Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","");
        String password = sharedPreferences.getString("password","");
        if (!StringUtil.isEmpty(username)){
            instance.userModel.setUsername(username);
            instance.userModel.setPassword(password);
            instance.remeberPassword.set(true);
        }
        return instance;
    }

    public void login() throws Throwable {


        Log.e("iii", String.valueOf(remeberPassword.get()));

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

        if (remeberPassword.get()){
            SharedPreferences sharedPreferences = context.getSharedPreferences("zbq",Context.MODE_PRIVATE);
            sharedPreferences.edit()
                    .putString("username",userModel.getUsername())
                    .putString("password",userModel.getPassword())
                    .apply();

        }
    }
}
