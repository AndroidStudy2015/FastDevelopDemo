package com.fast.develop.fastcore.app;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by apple on 2017/9/15.
 */

public final class Fast {

    //     1.init，一般会传递一个context作为初始化，同时把他存在了map里面，以后方便获取
    public static Configurator init(Context context) {

        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());

        return Configurator.getInstace();
    }

    public static HashMap<String, Object> getConfigurations() {

        return Configurator.getInstace().getFastConfigs();
    }

    public static Context getApplicationContext(){
        return Configurator.getInstace().getConfiguation(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static String getApiHost(){
        return Configurator.getInstace().getConfiguation(ConfigKeys.API_HOST);
    }

    public static ArrayList<Interceptor> getInterceptor(){
        return Configurator.getInstace().getConfiguation(ConfigKeys.INTERCEPTOR);
    }
}
