package wang.ismy.zbq.video;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import java.util.LinkedHashMap;
import java.util.Map;

public class VideoSearchModel extends BaseObservable {

    private Integer engine;

    private String kw;


    @Bindable
    public Integer getEngine() {
        return engine;
    }

    public void setEngine(Integer engine) {
        this.engine = engine;
        notifyChange();
    }

    @Bindable
    public String getKw() {
        return kw;
    }

    public void setKw(String kw) {
        this.kw = kw;
        notifyChange();
    }


}
