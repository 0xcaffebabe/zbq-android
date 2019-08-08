package wang.ismy.zbq.app;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONObject;

import java.io.IOException;


import okhttp3.Response;
import wang.ismy.zbq.model.Result;


public class ZbqResponse {

    private Result result;

    private Response response;

    public ZbqResponse(Response response) throws Throwable {
        this.response = response;
        String s = response.body().string();

        JsonElement jsonElement = new JsonParser().parse(s);
        result = new Result();
        result.setSuccess(jsonElement.getAsJsonObject().get("success").getAsBoolean());
        result.setMsg(jsonElement.getAsJsonObject().get("msg").getAsString());
        result.setData(jsonElement.getAsJsonObject().get("data").toString());

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
