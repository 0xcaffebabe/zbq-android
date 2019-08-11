package wang.ismy.zbq.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import wang.ismy.zbq.R;
import wang.ismy.zbq.activity.VideoSearchActivity;
import wang.ismy.zbq.util.StringUtil;

public class MenuItemView extends LinearLayout {

    private ImageView leftIcon;

    private TextView title;

    private String target;

    public MenuItemView(Context context) {
        super(context);
    }

    public MenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View view = inflate(context,R.layout.view_menu_item,this);

        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
            }
        });

        leftIcon = view.findViewById(R.id.menu_item_left_icon);
        title = view.findViewById(R.id.menu_item_title);

        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MenuItemView);


        leftIcon.setImageDrawable(ta.getDrawable(R.styleable.MenuItemView_leftIcon));
        title.setText(ta.getString(R.styleable.MenuItemView_title));
        target = ta.getString(R.styleable.MenuItemView_target);
        view.findViewById(R.id.menu_item_container).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!StringUtil.isEmpty(target)){
                    Intent intent = new Intent();
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    try {
                        intent.setClass(getContext(), Class.forName(target));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    getContext().startActivity(intent);
                }

            }
        });
        ta.recycle();


    }

    public MenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MenuItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
