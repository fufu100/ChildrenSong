package com.foreverrich.childrensong.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by lyf on 2017/4/8.
 */

public class Video implements Parcelable{
    public int channelid;
    public int commentCount ;
    public String desc ;
    public String image ;
    public String pdate ;
    public int subcateid ;
    public int tid ;
    public String title ;
    public String url ;
    public int vid ;
    public int viewCount ;
    public int ykid ;
    public String ykidstr ;

    protected Video(Parcel in) {
        channelid = in.readInt();
        commentCount = in.readInt();
        desc = in.readString();
        image = in.readString();
        pdate = in.readString();
        subcateid = in.readInt();
        tid = in.readInt();
        title = in.readString();
        url = in.readString();
        vid = in.readInt();
        viewCount = in.readInt();
        ykid = in.readInt();
        ykidstr = in.readString();
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(channelid);
        dest.writeInt(commentCount);
        dest.writeString(desc);
        dest.writeString(image);
        dest.writeString(pdate);
        dest.writeInt(subcateid);
        dest.writeInt(tid);
        dest.writeString(title);
        dest.writeString(url);
        dest.writeInt(vid);
        dest.writeInt(viewCount);
        dest.writeInt(ykid);
        dest.writeString(ykidstr);
    }
}
