package com.fast.develop.fastcore.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.fast.develop.fastcore.app.Fast;
import com.fast.develop.fastcore.net.callback.IRequest;
import com.fast.develop.fastcore.net.callback.ISuccess;
import com.fast.develop.fastcore.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by apple on 2017/4/2
 */

final class SaveFileTask extends AsyncTask<Object, Integer, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    SaveFileTask(IRequest REQUEST, ISuccess SUCCESS) {
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
    }

    @Override
    protected void onProgressUpdate(Integer... progeress) {//主线程，更新UI
        super.onProgressUpdate(progeress);
//        Log.e("qwe",progeress[0]+"");
//        在这里加载一个progressbar，显示进度
    }

    @Override
    protected File doInBackground(Object... params) {//子线程，请求数据，写入本地数据
        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String name = (String) params[3];
        final InputStream is = body.byteStream();


        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }

        FileUtil.ProgressCallBack progressCallBack = new FileUtil.ProgressCallBack() {

            @Override
            public void onProgressCallBack(int progress) {
                publishProgress(progress);

            }
        };
        if (name == null) {
            return FileUtil.writeToDisk(body, is, downloadDir, extension.toUpperCase(), extension, progressCallBack);
        } else {
            return FileUtil.writeToDisk(body, is, downloadDir, name, progressCallBack);
        }

    }

    @Override
    protected void onPostExecute(File file) {//主线程。更新UI
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Fast.getApplicationContext().startActivity(install);
        }
    }
}
