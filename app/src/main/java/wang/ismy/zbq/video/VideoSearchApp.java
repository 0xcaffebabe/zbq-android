package wang.ismy.zbq.video;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import wang.ismy.zbq.app.App;
import wang.ismy.zbq.app.ZbqResponse;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.util.StringUtil;

public class VideoSearchApp extends App {

    public VideoSearchModel videoSearchModel = new VideoSearchModel();

    public List<Video> videoList = new ArrayList<>();

    private static final VideoSearchApp instance = new VideoSearchApp();

    private VideoSearchApp() { }

    public void search() throws Throwable {

        String kw = videoSearchModel.getKw();
        Integer engine = videoSearchModel.getEngine();

        if (StringUtil.isEmpty(kw)){
            throw new AppException("搜索关键词不得为空");
        }


        ZbqResponse zbqResponse = get(URL.VIDEO_SEARCH+"?kw="+
                URLEncoder.encode(kw,"utf8")+"&engine="+engine+"&page=1&length=20");

        if (!zbqResponse.getResult().isSuccess()){
            throw new AppException(zbqResponse.getResult().getMsg());
        }

        List<Video> list= new Gson().fromJson(zbqResponse.getResult().getData(),new TypeToken<List<Video>>(){}.getType());

        videoList.addAll(list);

    }


    public static VideoSearchApp newInstance(){
        return instance;
    }


}
