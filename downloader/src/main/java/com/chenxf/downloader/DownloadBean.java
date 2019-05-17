package com.chenxf.downloader;

import android.os.Parcel;
import android.os.Parcelable;

public class DownloadBean implements Parcelable {
    String url;

    String downloadResult;

    public DownloadBean() {
    }

    public DownloadBean(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadResult() {
        return downloadResult;
    }

    public void setDownloadResult(String downloadResult) {
        this.downloadResult = downloadResult;
    }


    protected DownloadBean(Parcel in) {
        url = in.readString();
        downloadResult = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(downloadResult);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DownloadBean> CREATOR = new Creator<DownloadBean>() {
        @Override
        public DownloadBean createFromParcel(Parcel in) {
            return new DownloadBean(in);
        }

        @Override
        public DownloadBean[] newArray(int size) {
            return new DownloadBean[size];
        }
    };
}
