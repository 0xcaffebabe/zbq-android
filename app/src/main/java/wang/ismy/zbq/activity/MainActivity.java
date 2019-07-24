package wang.ismy.zbq.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import wang.ismy.zbq.R;
import wang.ismy.zbq.login.LoginApp;

public class MainActivity extends AppCompatActivity {

    private LoginApp loginApp = LoginApp.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
