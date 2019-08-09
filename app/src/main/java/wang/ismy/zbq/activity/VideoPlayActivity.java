package wang.ismy.zbq.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;


import com.tencent.smtt.export.external.interfaces.JsResult;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import lombok.val;
import wang.ismy.zbq.R;

public class VideoPlayActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        webView = findViewById(R.id.activity_video_play_web_view);

        initWebView();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                if (s.startsWith("http")){
                    webView.loadUrl(s);
                }

                return true;
            }
        });

        Intent intent = getIntent();

        if (intent!= null){
            webView.loadUrl(intent.getStringExtra("url"));

        }

    }

    private void initWebView() {
        WebSettings settings = webView.getSettings();           //和系统webview一样
        settings.setJavaScriptEnabled(true);                    //支持Javascript 与js交互
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        settings.setAllowFileAccess(true);                      //设置可以访问文件
        settings.setSupportZoom(true);                          //支持缩放
        settings.setBuiltInZoomControls(true);                  //设置内置的缩放控件
        settings.setUseWideViewPort(true);//自适应屏幕
        settings.setSupportMultipleWindows(true);               //多窗口
        settings.setDefaultTextEncodingName("utf-8");            //设置编码格式
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setUserAgent("Mozilla/5.0 (Linux; U; Android 5.0.2; zh-cn; X900 Build/CBXCNOP5500912251S) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025489 Mobile Safari/533.1 V1_AND_SQ_6.0.0_300_YYB_D QQ/6.0.0.2605 NetType/WIFI WebP/0.3.0 Pixel/1440");
        settings.setAppCacheMaxSize(Long.MAX_VALUE);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);       //缓存模式


    }


}
