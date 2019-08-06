package wang.ismy.zbq.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import wang.ismy.zbq.R;

public class VideoItemView extends LinearLayout {
    public VideoItemView(Context context) {
        super(context);
    }

    public VideoItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = inflate(context, R.layout.view_video_item, this);
        view.findViewById(R.id.view_video_search).setOnClickListener(new OnClickListener() {
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
}
