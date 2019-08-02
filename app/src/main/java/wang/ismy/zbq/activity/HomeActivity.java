package wang.ismy.zbq.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityHomeBinding;
import wang.ismy.zbq.databinding.ActivityMainBinding;
import wang.ismy.zbq.fragment.ContentFragment;
import wang.ismy.zbq.fragment.PersonFragment;
import wang.ismy.zbq.fragment.SocialFragment;
import wang.ismy.zbq.fragment.StudyFragment;
import wang.ismy.zbq.model.HomeModel;
import wang.ismy.zbq.model.TabItemModel;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    private List<Fragment> fragmentList =
            Arrays.asList(new SocialFragment(),new ContentFragment(),new StudyFragment(),new PersonFragment());

    private List<TabItemModel> tabList = Arrays.asList(new TabItemModel("社交",R.drawable.social),new TabItemModel("内容",R.drawable.content),
            new TabItemModel("学习",R.drawable.study),new TabItemModel("我的",R.drawable.my));

    private HomeModel homeModel = new HomeModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_home);
        homeModel.setTitle("社交");
        binding.setHome(homeModel);
        binding.homeContainer.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        for (TabItemModel t: tabList){
            TabLayout.Tab tab = binding.homeTabLayout.newTab();
            tab.setText(t.getText());
            tab.setIcon(t.getIcon());

            binding.homeTabLayout.addTab(tab);
        }
        binding.homeTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                binding.homeContainer.setCurrentItem(getTabItem(tab.getText().toString()));

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.homeContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TabLayout.Tab tab = binding.homeTabLayout.getTabAt(position);
                if (tab != null){
                    tab.select();
                    homeModel.setTitle(tabList.get(position).getText());
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private int getTabItem(String tabText){
        for(int i=0;i<tabList.size();i++){
            if (tabList.get(i).equals(tabText)){
                return i;
            }
        }
        return -1;
    }
}
