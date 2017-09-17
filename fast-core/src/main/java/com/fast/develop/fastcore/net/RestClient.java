package com.fast.develop.fastcore.net;

import android.content.Context;

import com.fast.develop.fastcore.net.callback.IError;
import com.fast.develop.fastcore.net.callback.IFailure;
import com.fast.develop.fastcore.net.callback.IRequest;
import com.fast.develop.fastcore.net.callback.ISuccess;
import com.fast.develop.fastcore.net.callback.RequestCallbacks;
import com.fast.develop.fastcore.ui.FastLoader;
import com.fast.develop.fastcore.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by apple on 2017/9/17.
 * 进行请求的具体实现类
 */

public class RestClient {
    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;


    public RestClient(String url, Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle fastLoaderStyle,
                      Context context) {
        URL = url;
        PARAMS.putAll(params);
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        BODY = body;
        LOADER_STYLE = fastLoaderStyle;
        CONTEXT = context;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();

        Call<String> call = null;
//        这里可以判断网络不存在，停止请求，弹出吐司
        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE!=null){
            FastLoader.showLoading(CONTEXT,LOADER_STYLE);
        }
        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;

            case POST:
                call = service.post(URL, PARAMS);
                break;

            case PUT:
                call = service.put(URL, PARAMS);
                break;

            case DELETE:
                call = service.delete(URL, PARAMS);
                break;

            default:
                break;
        }

//        不直接用retrofit的成功、失败回调，要再次封装一层自己的回调 class RequestCallBack implements retrofit.Callback
        if (call != null) {
            call.enqueue(getRequestCallBack());
        }
    }

    private Callback<String> getRequestCallBack() {

        return new RequestCallbacks(
                REQUEST,
                SUCCESS,
                FAILURE,
                ERROR,
                LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
