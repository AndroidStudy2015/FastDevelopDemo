package com.fast.develop.fastcore.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import com.fast.develop.fast.R;

/**
 * Created by apple on 2017/9/16.
 */

public abstract class NewProxyActivity extends AppCompatActivity {//SupportActivity是fragmentation里的

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("c", "llll");
        initContainer(savedInstanceState);
    }

//    public abstract FastFragment setRootFragment();

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        Log.e("c", "llll");
////        initContainer(savedInstanceState);
//    }

    /**
     * 设置根容器ContentFrameLayout
     *
     * @param savedInstanceState
     */
    private void initContainer(@Nullable Bundle savedInstanceState) {
//        代码new出一个framelayout，省的每次都在布局里再写
        FrameLayout container = new FrameLayout(this);
        container.setBackgroundColor(Color.RED);
        //如何设置ID----ids
        container.setId(R.id.delegate_container);

        setContentView(container);//把这个ContentFrameLayout作为Activity的根容器

//        if (savedInstanceState == null) {
//         加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(R.id.delegate_container, setRootFragment()).commit();
//            loadRootFragment(R.id.delegate_container, setRootFragment());
//        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        退出时候，做些垃圾回收，虽然不一定执行gc
        System.gc();
        System.runFinalization();
    }
}
