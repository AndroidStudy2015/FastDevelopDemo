package com.fast.develop.fastcore.interceptors;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;

import com.fast.develop.fastcore.utils.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by apple on 2017/9/18.
 */

public class DebugInterceptor extends BaseInterceptor {

    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = rawId;
    }

    /**
     * 模拟了一个响应体Response
     * @param chain
     * @param json
     * @return
     */
    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)//模拟请求成功，如果传递404，会在请求后，走error回调
                .addHeader("Content-Type", "application/json")
                //模拟响应内容为一个json，这个json是raw文件下的test.java里的内容
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                //模拟一个响应message
                .message("OK")
                //请求原来的请求
                .request(chain.request())
                //请求协议1.1
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);//读取raw目录中的文件,并返回为字符串
        return getResponse(chain, json);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        //拦截器，如果请求的url里包含自定义的一个字符，那么就让他返回模拟的响应体，不然就继续执行原来的请求,原样返回
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}

