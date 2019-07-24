package wang.ismy.zbq.app;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.http.ZbqCookieJar;

public abstract class App {

    protected static final OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .cookieJar(new ZbqCookieJar())
            .build();

    protected Gson JSON = new Gson();


    protected Response get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .get()
                .build();
        return httpClient.newCall(request).execute();
    }

    protected Response post(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .post(body)
                .build();
        return httpClient.newCall(request).execute();
    }

    protected Response put(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .put(body)
                .build();
        return httpClient.newCall(request).execute();
    }

    protected Response delete(String url, RequestBody body) throws IOException {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .delete(body)
                .build();
        return httpClient.newCall(request).execute();
    }

    protected Response delete(String url) throws IOException {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .delete()
                .build();
        return httpClient.newCall(request).execute();
    }


}
