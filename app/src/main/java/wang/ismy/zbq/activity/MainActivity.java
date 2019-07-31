package wang.ismy.zbq.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityMainBinding;
import wang.ismy.zbq.login.LoginApp;
import wang.ismy.zbq.login.UserModel;
import wang.ismy.zbq.system.ZbqApplication;

public class MainActivity extends AppCompatActivity {

    private LoginApp loginApp = LoginApp.newInstance();

    private ActivityMainBinding binding;

    private Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setUser(loginApp.userModel);
        binding.setPresenter(presenter);


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
                                presenter.showTip(ZbqApplication.getStr(R.string.login_success));
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
    }
}
