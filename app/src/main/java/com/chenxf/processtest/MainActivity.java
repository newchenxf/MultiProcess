package com.chenxf.processtest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chenxf.downloader.DownloadBean;
import com.chenxf.downloader.DownloadService;
import com.chenxf.downloader.IDownloadAidl;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private IDownloadAidl downloadAidl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }
        });
    }

    private void handleClick() {
        if(downloadAidl == null) {
            //点击时，才开启进程，没有start的话，系统不会有downloder进程
            startService();
        } else {
            try {
                DownloadBean result = downloadAidl.getMessage(new DownloadBean("http://xx.mp4"));
                Toast.makeText(MainActivity.this, "result " + result.getDownloadResult(), Toast.LENGTH_LONG).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(downloadAidl != null) {
            unbindService(conn);
        }
    }

    private void startService() {
    // 创建所需要绑定的Service的Intent
//        Intent intent = new Intent();
//        intent.setAction("com.chenxf.downloader.action.START_SERVICE");
//        intent.setPackage("com.chenxf.downloader");
//        // 绑定远程的服务
//        bindService(intent, conn, Service.BIND_AUTO_CREATE);
        Intent intent = new Intent(this, DownloadService.class);
        bindService(intent, conn, Service.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "onServiceDisconnected " +name);

            downloadAidl = null;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 获取远程Service的onBinder方法返回的对象代理
            downloadAidl = IDownloadAidl.Stub.asInterface(service);
            Log.i(TAG, "onServiceConnected " +name +" downloadAidl" +downloadAidl );
        }
    };


}
