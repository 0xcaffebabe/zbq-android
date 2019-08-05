package wang.ismy.zbq.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import wang.ismy.zbq.R;

public class TextBoxView extends LinearLayout {

    private String leftText;

    private String hint;

    private Drawable rightIcon;

    private String inputType;

    public TextBoxView(Context context) {
        super(context);
    }

    public TextBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TextBoxView);

        View view = inflate(context,R.layout.view_text_box,this);

        leftText = ta.getString(R.styleable.TextBoxView_leftText);
        hint = ta.getString(R.styleable.TextBoxView_hint);
        rightIcon = ta.getDrawable(R.styleable.TextBoxView_rightIcon);

        TextView textView = view.findViewById(R.id.text_box_left_text);
        textView.setText(leftText);
        EditText editText = view.findViewById(R.id.text_box_edit_text);
        editText.setHint(hint);
        ImageView imageView = view.findViewById(R.id.text_box_right_icon);
        imageView.setImageDrawable(rightIcon);
        inputType = ta.getString(R.styleable.TextBoxView_inputMode);

        if ("text".equals(inputType)){
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
        }else if ("textPassword".equals(inputType)){
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }


        ta.recycle();
    }

    public TextBoxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public TextBoxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
