package com.fast.develop.fastcore.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by apple on 2017/9/15.
 */

public final class Fast {

    //     1.init，一般会传递一个context作为初始化，同时把他存在了map里面，以后方便获取
    public static Configurator init(Context context) {

        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());

        return Configurator.getInstace();
    }

    private static HashMap<String, Object> getConfigurations() {

        return Configurator.getInstace().getFastConfigs();
    }

    public static Context getApplicationContext(){
        return Configurator.getInstace().getConfiguation(ConfigType.APPLICATION_CONTEXT);
    }
}
