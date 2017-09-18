package com.fast.develop.fastcore.interceptors;

import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

/**
 * Created by apple on 2017/9/18.
 */

public abstract class BaseInterceptor implements Interceptor {

    /**
     * 得到所有的url参数集合（Get请求），把所有的url后面带的参数，全部封装到LinkedHashMap
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getUrlParameters(Chain chain) {
        //得到所有的url参数
        final HttpUrl url = chain.request().url();
        //得到所有url参数个数
        int size = url.querySize();
        //把所有的url参数按照键值对存到LinkedHashMap里
        final LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            // queryParameterName（i）,第i个参数的参数名key
            // queryParameterValue（i）,第i个参数的参数值value
            params.put(url.queryParameterName(i), url.queryParameterValue(i));

        }
        return params;

    }

    /**
     * 通过某一个参数名称，得到参数值(Get请求）
     *
     * @param chain
     * @param key
     * @return
     */
    protected String getUrlParameters(Chain chain, String key) {
        Request request = chain.request();
        return request.url().queryParameter(key);
    }

    /**
     * 得到所有的url参数集合（Post请求），把所有的url后面带的参数，全部封装到LinkedHashMap
     *
     * @param chain
     * @return
     */
    protected LinkedHashMap<String, String> getBodyParameters(Chain chain) {
        FormBody body = (FormBody) chain.request().body();
        int size = body.size();
        LinkedHashMap<String, String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(body.name(i), body.value(i));
        }
        return params;
    }

    protected String getBodyParameters(Chain chain, String key) {
        return getBodyParameters(chain).get(key);

    }
}
