package wang.ismy.zbq.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityMainBinding;
import wang.ismy.zbq.login.LoginApp;
import wang.ismy.zbq.login.UIModel;
import wang.ismy.zbq.login.UserModel;
import wang.ismy.zbq.system.ZbqApplication;
import wang.ismy.zbq.util.UIUtil;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {

    private LoginApp loginApp;

    private ActivityMainBinding binding;

    private UIModel uiModel = new UIModel();

    private Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginApp = LoginApp.newInstance(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setUser(loginApp.userModel);
        binding.setPresenter(presenter);
        binding.setRememberPassword(loginApp.remeberPassword);
        binding.setUserCount(loginApp.userCount);
        binding.setOnlineUserCount(loginApp.onlineUserCount);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    loginApp.countUser();
                    loginApp.countOnlineUser();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    presenter.showTip(throwable.getMessage());
                }
            }
        }).start();
        autoLogin();


    }

    private void autoLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("zbq", Context.MODE_PRIVATE);

        String username = sharedPreferences.getString("username",null);

        if (username != null){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"3秒后自动登录",Toast.LENGTH_SHORT).show();
                        }
                    });
                    try {
                        Thread.sleep(3000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    presenter.loginClick();

                }
            }).start();

        }
    }
    public class Presenter {

        public void loginClick() {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        loginApp.login();
                        // 登录成功

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Intent intent = new Intent();

                                intent.setClass(getApplicationContext(),HomeActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);
                            }
                        });
                    } catch (final Throwable throwable) {
                        throwable.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.showTip(throwable.getMessage());
                            }
                        });

                    }
                }
            }).start();
        }

        public void registerClick() {

            showTip(ZbqApplication.getStr(R.string.unrealized));

        }

        public void showTip(String str) {
            new AlertDialog.Builder(MainActivity.this, R.style.AlterDialogCustom)
                    .setTitle(ZbqApplication.getStr(R.string.tip))
                    .setMessage(str)
                    .create().show();
        }

        public void clearUsername(){
            loginApp.userModel.setUsername("");

        }

        public void clearPassword(){

            if (binding.mainActivityPasswordTextBox.getInputType()
                    == (InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_NORMAL)) {
                binding.mainActivityPasswordTextBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                binding.mainActivityPasswordTextBox.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            }

            binding.mainActivityPasswordTextBox.setSelection(binding.mainActivityPasswordTextBox.getText().length());

        }

        public void register(){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setClass(getApplicationContext(),RegisterActivity.class);
            startActivity(intent);
        }
    }
}
