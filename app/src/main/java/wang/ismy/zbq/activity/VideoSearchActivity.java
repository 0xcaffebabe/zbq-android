package wang.ismy.zbq.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import wang.ismy.zbq.R;
import wang.ismy.zbq.databinding.ActivityVideoSearchBinding;
import wang.ismy.zbq.system.ZbqApplication;
import wang.ismy.zbq.video.HotKeyword;
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
                // 切换引擎
                String kw = getResources().getStringArray(R.array.video_engine)[i];
                binding.videoSearchEngine.setText(kw);
                app.videoSearchModel.setEngine(i);
                app.resetPageNumber();

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

        // 获取热词
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    app.getHotKw();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            initHotKw();
                        }
                    });
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                    new Presenter().showTip(throwable.getMessage());
                }
            }
        }).start();
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
        linearLayout.removeAllViews();
        for (Video i:app.videoList){
            VideoItemView videoItemView = new VideoItemView(getApplicationContext());
            videoItemView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            videoItemView.setVideo(i);
            linearLayout.addView(videoItemView);
        }

        Button button = new Button(getApplicationContext());
        button.setText("加载更多");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            app.search();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    initList();
                                }
                            });
                        } catch (final Throwable throwable) {
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
            }
        });
        linearLayout.addView(button);

    }

    public void initHotKw(){
        LinearLayout linearLayout = findViewById(R.id.activity_video_search_hot_kw);

        for (HotKeyword kw:app.hotKw){
            final Button button = new Button(getApplicationContext());
            button.setText(kw.getKw());
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.videoSearchInput.setQuery(button.getText(),true);
                    binding.videoSearchInput.requestFocus();
                    app.videoList.clear();
                }
            });
            linearLayout.addView(button);
        }
    }
}
