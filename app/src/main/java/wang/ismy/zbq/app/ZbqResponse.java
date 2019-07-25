package wang.ismy.zbq.app;

import com.google.gson.Gson;

import java.io.IOException;


import okhttp3.Response;
import wang.ismy.zbq.model.Result;


public class ZbqResponse {

    private Result result;

    private Response response;

    public ZbqResponse(Response response) throws Throwable {
        this.response = response;

        result = new Gson().fromJson(response.body().string(), Result.class);
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
