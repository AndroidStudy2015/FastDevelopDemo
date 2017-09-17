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
        testRequestClient();
    }


    private void testRequestClient() {

        RestClient.builder().url("https://www.baidu.com").params("", "").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {

            }
        }).error(new IError() {
            @Override
            public void onError(int code, String msg) {

            }
        }).onRequest(new IRequest() {
            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestEnd() {

            }
        }).build().get();
    }
}
