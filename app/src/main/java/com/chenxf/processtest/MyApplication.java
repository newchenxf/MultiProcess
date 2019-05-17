package com.chenxf.processtest;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

public class MyApplication extends Application {
    private static final String TAG = "MyApplication";
    private static final String DOWNLOADER_PROCESS = ":downloader";
    private static final String PLUGIN_PROCESS = ":plugin";

    private BaseApplication mProxy;

    @Override
    public void onCreate() {
        super.onCreate();
        String processName = getMyProcessName();
        Log.i(TAG, "onCreate " + processName);
        initProxyApplication(processName);
    }

    private void initProxyApplication(String processName) {
        String mPackageName = getPackageName();

        if (TextUtils.equals(mPackageName, processName)) {
            //主进程
            Log.i(TAG, "init process " + mPackageName);
            mProxy = new MainApplication(processName);
        } else if (TextUtils.equals(processName, mPackageName + PLUGIN_PROCESS)) {
            //插件安装进程
            Log.i(TAG, "init process " + PLUGIN_PROCESS);
            mProxy = new PluginApplication(processName);
        } else if (TextUtils.equals(processName, mPackageName + DOWNLOADER_PROCESS)) {
            //下载进程
            Log.i(TAG, "init process " + DOWNLOADER_PROCESS);
            mProxy = new DownloaderApplication(processName);
        } else {
            mProxy = new BaseApplication(processName);
        }
    }

    /**
     * 获取进程的名称
     *
     * @return
     */
    public String getMyProcessName() {
        if (mProxy != null) {
            return mProxy.getProcessName();
        } else {
            return initCurrentProcessName(this);
        }
    }

    private String initCurrentProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                return process.processName;
            }
        }
        return null;
    }
}