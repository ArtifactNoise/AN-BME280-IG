package com.example.test03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.example.artifactnoise_bme280.BME280;
import com.example.artifactnoise_bme280.BME280_JavaScriptAPI;


public class MainActivity extends AppCompatActivity {

    public static TextView textView;
    public static WebView myWebView;
    public static Button button ;
    private boolean button_flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //上のバーを消す
        getSupportActionBar().hide();

        String URL;

        URL ="file:///android_asset/index.html";
        myWebView = findViewById(R.id.webview);
        myWebView.setVisibility(View.INVISIBLE);
        myWebView.setWebContentsDebuggingEnabled(true);
        // Javascriptを有効に。
        myWebView.getSettings().setJavaScriptEnabled(true);
        // リンクをクリックしても同じWebViewを立ち上げる
        myWebView.setWebViewClient(new WebViewClient());

        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.getSettings().setLoadWithOverviewMode(true);
        myWebView.getSettings().setSupportZoom(true);
        // キャッシュ・履歴の削除
        myWebView.clearCache(true);
        myWebView.clearHistory();
        //String URL = RootStr;
        myWebView.getSettings().setAllowFileAccessFromFileURLs(true);

        String userAgent = myWebView.getSettings().getUserAgentString();
        myWebView.getSettings().setUserAgentString(userAgent +" Webview");

        final BME280_JavaScriptAPI  bme280 = new BME280_JavaScriptAPI(this);
        myWebView.addJavascriptInterface(bme280, "android");
        // クッキー有効化
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeAllCookie();
        cookieManager.setCookie(URL,"");
        cookieManager.setAcceptThirdPartyCookies(myWebView, true);

        myWebView.loadUrl(URL);
        myWebView.setVisibility(View.VISIBLE);


        /*
        final BME280 bme280 = new BME280(this);
        final TextView textView = findViewById(R.id.textView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true)
                {
                    while(bme280.STATE_String().equals("SUCCESS"))
                    {
                        bme280.Init();
                        bme280.readData();
                        StringBuffer strbuf = new StringBuffer();
                        strbuf.append(String.format("temp : %-6.2f ℃ \n", bme280.Temperature()));
                        strbuf.append(String.format("hum : %6.2f ％ \n", bme280.Humidity()));
                        strbuf.append(String.format("pressure : %7.2f hPa \n", bme280.Pressure() / 100));
                        strbuf.append("OK. \n");
                        textView.setText(strbuf);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                    }
                }
            }
        }).start();
        */
        //--------------------------------------------------//
        //　Menu ボタン動作記述
        //--------------------------------------------------//
        /*
        myWebView = findViewById(R.id.webview);
        myWebView.setVisibility(View.INVISIBLE);
        final BME280_JavaScriptAPI  bme280 = new BME280_JavaScriptAPI(this);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(button_flag==true)
                {
                    button_flag = false;
                    String URL;

                    URL ="file:///android_asset/index.html";

                    myWebView.setWebContentsDebuggingEnabled(true);
                    // Javascriptを有効に。
                    myWebView.getSettings().setJavaScriptEnabled(true);
                    // リンクをクリックしても同じWebViewを立ち上げる
                    myWebView.setWebViewClient(new WebViewClient());

                    myWebView.getSettings().setDomStorageEnabled(true);
                    myWebView.getSettings().setUseWideViewPort(true);
                    myWebView.getSettings().setLoadWithOverviewMode(true);
                    myWebView.getSettings().setSupportZoom(true);
                    // キャッシュ・履歴の削除
                    myWebView.clearCache(true);
                    myWebView.clearHistory();
                    //String URL = RootStr;
                    myWebView.getSettings().setAllowFileAccessFromFileURLs(true);

                    String userAgent = myWebView.getSettings().getUserAgentString();
                    myWebView.getSettings().setUserAgentString(userAgent +" Webview");

                    myWebView.addJavascriptInterface(bme280, "android");
                    // クッキー有効化
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.setAcceptCookie(true);
                    cookieManager.removeAllCookie();
                    cookieManager.setCookie(URL,"");
                    cookieManager.setAcceptThirdPartyCookies(myWebView, true);

                    myWebView.loadUrl(URL);
                    myWebView.setVisibility(View.VISIBLE);
                }
                else
                {
                    button_flag = true;
                    myWebView.setVisibility(View.INVISIBLE);
                }
            }
        });
        */
    }
}
