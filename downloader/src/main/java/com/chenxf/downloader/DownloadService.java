package com.chenxf.downloader;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class DownloadService extends Service {
    private DownloadServiceStub downloadServiceStub;
    public DownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        downloadServiceStub = new DownloadServiceStub();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return downloadServiceStub;
    }

    public class DownloadServiceStub extends IDownloadAidl.Stub {


        @Override
        public void sendMessage(DownloadBean url) throws RemoteException {

        }

        @Override
        public DownloadBean getMessage(DownloadBean param) throws RemoteException {
            DownloadBean result = new DownloadBean();
            result.setDownloadResult("/sdcard/xx.mp4");
            return result;
        }
    }
}
