package wang.ismy.zbq.fragment;


import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import wang.ismy.zbq.R;
import wang.ismy.zbq.activity.MainActivity;
import wang.ismy.zbq.databinding.FragmentPersonBinding;
import wang.ismy.zbq.person.PersonApp;
import wang.ismy.zbq.person.UserInfo;
import wang.ismy.zbq.system.ZbqApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {


    private FragmentPersonBinding binding;

    private PersonApp personApp = PersonApp.newInstance();

    private Presenter presenter = new Presenter();

    private UserInfo userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_person, container, false);


            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        userInfo = personApp.getSelfUserInfo();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadUserInfo(userInfo);

                            }
                        });
                        binding.setUserInfo(userInfo);
                    } catch (final Throwable throwable) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                presenter.showTip(throwable.getMessage());
                            }
                        });

                        throwable.printStackTrace();
                    }

                }
            }).start();



        return binding.getRoot();

    }

    private void loadUserInfo(UserInfo userInfo){

        Glide.with(binding.getRoot())
                .load(userInfo.getProfile())
                .placeholder(R.drawable.my)
                .into(binding.fragmentPersonProfile);
    }

    public class Presenter{
        public void showTip(String str) {
            new AlertDialog.Builder(getContext(), R.style.AlterDialogCustom)
                    .setTitle(ZbqApplication.getStr(R.string.tip))
                    .setMessage(str)
                    .create().show();
        }
    }
}
