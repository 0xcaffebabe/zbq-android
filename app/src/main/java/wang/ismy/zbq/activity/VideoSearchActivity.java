package wang.ismy.zbq.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityVideoSearchBinding;
import wang.ismy.zbq.system.ZbqApplication;
import wang.ismy.zbq.video.Video;
import wang.ismy.zbq.video.VideoSearchApp;
import wang.ismy.zbq.view.VideoItemView;

public class VideoSearchActivity extends AppCompatActivity {

    private ActivityVideoSearchBinding binding;

    private VideoSearchApp app = VideoSearchApp.newInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_video_search);

        binding.setModel(app.videoSearchModel);

        binding.videoSearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String kw = getResources().getStringArray(R.array.video_engine)[i];
                binding.videoSearchEngine.setText(kw);
                app.videoSearchModel.setEngine(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.videoSearchInput.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                app.videoSearchModel.setKw(s);

                // 搜索
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            app.search();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try{
                                        initList();
                                    }catch (Throwable t){
                                        t.printStackTrace();
                                    }

                                }
                            });
                        }catch (final Throwable throwable){
                            throwable.printStackTrace();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    new Presenter().showTip(throwable.getMessage());
                                }
                            });

                        }

                    }
                }).start();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    public class Presenter{
        public void showTip(String str) {
            new AlertDialog.Builder(VideoSearchActivity.this, R.style.AlterDialogCustom)
                    .setTitle(ZbqApplication.getStr(R.string.tip))
                    .setMessage(str)
                    .create().show();
        }
    }

    public void initList(){
        LinearLayout linearLayout = findViewById(R.id.activity_video_search_list);
        for (Video i:app.videoList){
            VideoItemView videoItemView = new VideoItemView(getApplicationContext());
            videoItemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            videoItemView.setVideo(i);
            linearLayout.addView(videoItemView);
        }



    }
}
