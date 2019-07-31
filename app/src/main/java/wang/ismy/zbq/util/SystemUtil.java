package wang.ismy.zbq.util;

import android.app.Application;

import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.system.ZbqApplication;

public class SystemUtil {

    public static void error(int id){

        throw new AppException(ZbqApplication.getInstance().getString(id));
    }
}
