package com.fast.develop.fastcore.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.fast.develop.fast.R;
import com.fast.develop.fastcore.delegates.FastDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by apple on 2017/9/16.
 */

public abstract class ProxyActivity extends SupportActivity {//SupportActivity是fragmentation里的

    public abstract FastDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("c","llll");
        initContainer(savedInstanceState);
    }

    /**
     * 设置根容器ContentFrameLayout
     *
     * @param savedInstanceState
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
//        代码new出一个framelayout，省的每次都在布局里再写
        final ContentFrameLayout container = new ContentFrameLayout(this);
        //如何设置ID----ids
        container.setId(R.id.delegate_container);
        setContentView(container);//把这个ContentFrameLayout作为Activity的根容器
        if (savedInstanceState == null) {
            // 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        退出时候，做些垃圾回收，虽然不一定执行gc
        System.gc();
        System.runFinalization();
    }
}
