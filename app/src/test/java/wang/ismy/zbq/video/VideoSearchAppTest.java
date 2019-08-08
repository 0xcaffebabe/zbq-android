package wang.ismy.zbq.video;

import org.junit.Test;

import static org.junit.Assert.*;

public class VideoSearchAppTest {

    @Test
    public void search() throws Throwable {
        VideoSearchApp app = VideoSearchApp.newInstance();

        app.videoSearchModel.setKw("15f6dsa1f56sd");
        app.videoSearchModel.setEngine(0);

        app.search();
        System.out.println(app.videoList.size());

        System.out.println(app.videoList);
    }
}