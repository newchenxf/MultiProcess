// IDownloadAidl.aidl
package com.chenxf.downloader;
// Declare any non-default types here with import statements

interface IDownloadAidl {
    void sendMessage(String url);
    int getMessage(int param);
}
