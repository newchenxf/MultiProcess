// IDownloadAidl.aidl
package com.chenxf.downloader;
import com.chenxf.downloader.DownloadBean;
// Declare any non-default types here with import statements

interface IDownloadAidl {
    void sendMessage(in DownloadBean url);//注意加in，不然辩不过
    DownloadBean getMessage(in DownloadBean param);
}
