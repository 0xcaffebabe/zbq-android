package wang.ismy.zbq.app;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import wang.ismy.zbq.constant.URL;
import wang.ismy.zbq.http.ZbqCookieJar;

public abstract class App {

    private static final OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .cookieJar(new ZbqCookieJar())
            .build();

    protected Gson JSON = new Gson();


    protected ZbqResponse get(String url) throws Throwable {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .get()
                .build();

        return new ZbqResponse(httpClient.newCall(request).execute());
    }



    protected ZbqResponse post(String url, RequestBody body) throws Throwable {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .post(body)
                .build();
        return new ZbqResponse(httpClient.newCall(request).execute());
    }

    protected ZbqResponse put(String url, RequestBody body) throws Throwable {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .put(body)
                .build();
        return new ZbqResponse(httpClient.newCall(request).execute());
    }

    protected ZbqResponse delete(String url, RequestBody body) throws Throwable {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .delete(body)
                .build();
        return new ZbqResponse(httpClient.newCall(request).execute());
    }

    protected ZbqResponse delete(String url) throws Throwable {
        Request request = new Request.Builder()
                .url(URL.BASE_URL + url)
                .delete()
                .build();
        return new ZbqResponse(httpClient.newCall(request).execute());
    }




}
