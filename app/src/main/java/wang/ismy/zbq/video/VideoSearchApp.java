package wang.ismy.zbq.video;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import wang.ismy.zbq.app.App;
import wang.ismy.zbq.app.ZbqResponse;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.exception.AppException;
import wang.ismy.zbq.util.StringUtil;

public class VideoSearchApp extends App {

    public VideoSearchModel videoSearchModel = new VideoSearchModel();

    public List<Video> videoList = new ArrayList<>();

    public List<HotKeyword> hotKw = new ArrayList<>();

    private static final VideoSearchApp instance = new VideoSearchApp();

    private int pageNumber = 0;

    private VideoSearchApp() { }

    public void search() throws Throwable {
        pageNumber++;

        String kw = videoSearchModel.getKw();
        Integer engine = videoSearchModel.getEngine();

        if (StringUtil.isEmpty(kw)){
            throw new AppException("搜索关键词不得为空");
        }

        ZbqResponse zbqResponse = get(URL.VIDEO_SEARCH+"?kw="+
                URLEncoder.encode(kw,"utf8")+"&engine="+engine+"&page="+pageNumber+"&length=20");

        if (!zbqResponse.getResult().isSuccess()){
            throw new AppException(zbqResponse.getResult().getMsg());
        }

        List<Video> list= new Gson().fromJson(zbqResponse.getResult().getData(),new TypeToken<List<Video>>(){}.getType());

        if (list.size() == 0){
            throw new AppException("没有更多数据");
        }

        videoList.addAll(list);

    }

    public void resetPageNumber(){
        pageNumber = 0;
        videoList.clear();
    }

    public void getHotKw() throws Throwable {

        ZbqResponse response = get(URL.VIDEO_SEARCH_HOT_KW);

        if (!response.getResult().isSuccess()){
            throw new AppException(response.getResult().getMsg());
        }

        hotKw = new Gson().fromJson(response.getResult().getData(),new TypeToken<List<HotKeyword>>(){}.getType());



    }

    public static VideoSearchApp newInstance(){
        return instance;
    }


}
