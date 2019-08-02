package wang.ismy.zbq.util;

import android.content.Context;
import android.content.Intent;

public class UIUtil {

    public static void openActivity(Context context,Class klass){
        Intent intent = new Intent();
        intent.setClass(context,klass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
