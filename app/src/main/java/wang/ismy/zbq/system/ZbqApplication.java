package wang.ismy.zbq.system;

import android.app.Application;

public class ZbqApplication extends Application {

    private static ZbqApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static ZbqApplication getInstance(){
        return instance;
    }

    public static String getStr(int id){
        return instance.getString(id);
    }

}
