package wang.ismy.zbq.system;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

public class ZbqApplication extends Application {

    private static ZbqApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback callback = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg) {
                //x5內核初始化完成的回调，
                // true表示x5内核加载成功，
                // false表示x5内核加载失败，会自动切换到系统内核。
            }

            @Override
            public void onCoreInitFinished() {
            }
        };

        QbSdk.initX5Environment(getApplicationContext(), callback);

    }

    public static ZbqApplication getInstance(){
        return instance;
    }

    public static String getStr(int id){
        return instance.getString(id);
    }

}
