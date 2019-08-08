package wang.ismy.zbq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import wang.ismy.zbq.R;
import wang.ismy.zbq.video.Video;

public class VideoItemView extends LinearLayout {

    private View view;

    public VideoItemView(Context context) {


        super(context);
        view = inflate(context, R.layout.view_video_item, this);
    }

    public VideoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        view = inflate(context, R.layout.view_video_item, this);

        view.findViewById(R.id.view_video_item).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public VideoItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VideoItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setVideo(Video video){

        ImageView img = view.findViewById(R.id.view_video_item_img);
        TextView title = view.findViewById(R.id.view_video_item_title);
        TextView subtitle = view.findViewById(R.id.view_video_item_subtitle);

        title.setText(video.getTitle());
        String thumbnail = video.getThumbnail();

        if (!thumbnail.startsWith("http")){
            thumbnail = "http:"+thumbnail;
        }
        Glide.with(getContext())
                .load(thumbnail)
                .placeholder(R.drawable.zbtt1)
                .into(img);

    }
}
