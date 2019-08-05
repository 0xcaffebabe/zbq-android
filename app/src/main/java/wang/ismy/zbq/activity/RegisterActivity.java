package wang.ismy.zbq.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityRegisterBinding;
import wang.ismy.zbq.register.RegisterApp;
import wang.ismy.zbq.system.ZbqApplication;
import wang.ismy.zbq.util.UIUtil;

public class RegisterActivity extends AppCompatActivity {

    private RegisterApp registerApp = RegisterApp.newInstance();

    private ActivityRegisterBinding binding;

    private Presenter presenter = new Presenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);

        binding.setRegister(registerApp.registerModel);
        binding.setPresenter(presenter);

    }

    public class Presenter{

        public void register(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final String ret = registerApp.register();
                        // 注册成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.showTip(ret, new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        presenter.backLogin();
                                    }
                                });
                            }
                        });


                    } catch (final Throwable throwable) {
                        throwable.printStackTrace();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.showTip(throwable.getMessage(), new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialogInterface) {

                                    }
                                });
                            }
                        });
                    }
                }
            }).start();
        }

        public void backLogin(){
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setClass(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        public void clearUsername(){
            registerApp.registerModel.setUsername("");
        }

        public void showPassword(){
            if (binding.registerActivityPassword.getInputType()
                    == (InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_NORMAL)) {
                binding.registerActivityPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                binding.registerActivityPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            }

            binding.registerActivityPassword.setSelection(binding.registerActivityPassword.getText().length());
        }

        public void showRepeatPassword(){
            if (binding.registerActivityRepeatPassword.getInputType()
                    == (InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_NORMAL)) {
                binding.registerActivityRepeatPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }else {
                binding.registerActivityRepeatPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            }

            binding.registerActivityRepeatPassword.setSelection(binding.registerActivityRepeatPassword.getText().length());
        }

        public void showTip(String str,DialogInterface.OnDismissListener dismissListener) {
            new AlertDialog.Builder(RegisterActivity.this, R.style.AlterDialogCustom)
                    .setTitle(ZbqApplication.getStr(R.string.tip))
                    .setMessage(str)
                    .setOnDismissListener(dismissListener)
                    .create().show();
        }
    }
}
