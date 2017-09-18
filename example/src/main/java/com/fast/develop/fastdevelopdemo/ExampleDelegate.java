package com.fast.develop.fastdevelopdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fast.develop.fastcore.delegates.FastDelegate;
import com.fast.develop.fastcore.net.RestClient;
import com.fast.develop.fastcore.net.callback.IError;
import com.fast.develop.fastcore.net.callback.IFailure;
import com.fast.develop.fastcore.net.callback.IRequest;
import com.fast.develop.fastcore.net.callback.ISuccess;

/**
 * Created by apple on 2017/9/16.
 */

public class ExampleDelegate extends FastDelegate {
    @Override
    public Object setLayout() {
        Log.e("qqqq", "fd");
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
       rootView.findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRequestClient();

            }
        });
    }

    private void testRequestClient() {
        String baiduurl = "https://www.baidu.com";
        String url = "http://27.221.23.220/download.sj.qq.com/upload/connAssitantDownload/upload/MobileAssistant_1.apk?mkey=59bf0f3a440ffe28&f=d488&c=0&p=.apk";
        String moniUrl = "http://127.0.0.1/index";
        RestClient.builder().url(baiduurl).params("", "").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
//                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(), "成功了" + response, Toast.LENGTH_LONG).show();

            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "失败了", Toast.LENGTH_LONG).show();

            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {
                Toast.makeText(getContext(), "错误", Toast.LENGTH_LONG).show();

            }
        }).onRequest(new IRequest() {
            @Override
            public void onRequestStart() {
                Toast.makeText(getContext(), "开始", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onRequestEnd() {
                Toast.makeText(getContext(), "结束", Toast.LENGTH_LONG).show();


            }
        }).name("a应用宝").extension("apk").build().get();
    }
}
