package wang.ismy.zbq.http;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public class ZbqCookieJar implements CookieJar {

    private Map<String,List<Cookie>> cookieMap = new HashMap<>();

    @NotNull
    @Override
    public List<Cookie> loadForRequest(@NotNull HttpUrl httpUrl) {

        List<Cookie> cookieList = cookieMap.get(httpUrl.host()+":"+httpUrl.port());
        if (cookieList != null){
            return cookieList;
        }
        return Collections.emptyList();
    }

    @Override
    public void saveFromResponse(@NotNull HttpUrl httpUrl, @NotNull List<Cookie> list) {
        cookieMap.put(httpUrl.host()+":"+httpUrl.port(),list);
    }
}
