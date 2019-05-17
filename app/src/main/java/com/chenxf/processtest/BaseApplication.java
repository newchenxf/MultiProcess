package com.chenxf.processtest;

import android.app.Application;
import android.util.Log;

public class BaseApplication {
    public static String TAG = "BaseApplication";
    protected String mProcessName = "";
    protected Application mContext;

    public BaseApplication(String processName) {
        Log.i(TAG, "BaseApplication init, processName " + processName);
        mProcessName = processName;
    }

    public void attach(Application application) {
        mContext = application;
        //TODO
    }

    public String getProcessName() {
        return mProcessName;
    }
}
