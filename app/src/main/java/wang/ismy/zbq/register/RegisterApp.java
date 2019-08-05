package wang.ismy.zbq.register;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import wang.ismy.zbq.app.App;
import wang.ismy.zbq.app.ZbqResponse;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.login.UserModel;
import wang.ismy.zbq.util.DigestUtil;
import wang.ismy.zbq.util.StringUtil;

public class RegisterApp extends App {

    private static final RegisterApp instance = new RegisterApp();

    public RegisterModel registerModel = new RegisterModel();

    private RegisterApp() {

    }

    public String register() throws Throwable {
        if (StringUtil.isEmpty(registerModel.getUsername())){
            throw new AppException("用户名不得为空");
        }

        if (StringUtil.isEmpty(registerModel.getPassword())){
            throw new AppException("密码不得为空");
        }

        if (StringUtil.isEmpty(registerModel.getRepeatPassword())){
            throw new AppException("请重复输入一遍密码");
        }

        if (!registerModel.getPassword().equals(registerModel.getRepeatPassword())){
            throw new AppException("两次输入的密码不一致");
        }

        UserModel userModel = new UserModel();
        userModel.setUsername(registerModel.getUsername());
        userModel.setPassword(DigestUtil.md5(registerModel.getPassword()).toUpperCase());

        String json = new Gson().toJson(userModel);

        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));

        ZbqResponse response = put(URL.REGISTER_URL,body);

        if (response.getResult().isSuccess()){
            return response.getResult().getData();
        }else{
            throw new AppException(response.getResult().getMsg());
        }
    }

    public static RegisterApp newInstance(){
        return instance;
    }
}
