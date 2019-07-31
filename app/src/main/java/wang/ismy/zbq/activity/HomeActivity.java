package wang.ismy.zbq.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityHomeBinding;
import wang.ismy.zbq.databinding.ActivityMainBinding;
import wang.ismy.zbq.fragment.SocialFragment;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);

        binding.homeContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new SocialFragment();
            }

            @Override
            public int getCount() {
                return 4;
            }
        });
    }
}
