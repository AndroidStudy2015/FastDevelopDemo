package com.fast.develop.fastdevelopdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.fast.develop.fastcore.delegates.FastDelegate;

/**
 * Created by apple on 2017/9/16.
 */

public class ExampleDelegate extends FastDelegate{
    @Override
    public Object setLayout() {
        Log.e("qqqq","fd");
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
