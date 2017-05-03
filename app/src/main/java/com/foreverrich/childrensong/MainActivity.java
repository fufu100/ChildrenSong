package com.foreverrich.childrensong;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.foreverrich.childrensong.Api.Api;
import com.foreverrich.childrensong.adapter.IItemView;
import com.foreverrich.childrensong.adapter.ItemViewCreator;
import com.foreverrich.childrensong.adapter.VideoViewHolder;
import com.foreverrich.childrensong.bean.Video;
import com.foreverrich.childrensong.bean.VideoListResp;
import com.foreverrich.childrensong.lib.BaseRecyclerViewActivity;
import com.foreverrich.childrensong.utils.ActivityUtils;

import java.util.ArrayList;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by lyf on 2017/4/22.
 */

public class MainActivity extends BaseRecyclerViewActivity<Video> {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestData();
    }

    @Override
    protected void requestData() {
        Api.getInstance().getGsService().getVideoList("53",1,45)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VideoListResp>() {
                    @Override
                    public void call(VideoListResp videoListResp) {
                        if(videoListResp.result == 1){
                            mList.clear();
                            mList.addAll(videoListResp.items);
                            getAdapter().isShowFooterItem(false);
                            getAdapter().notifyDataSetChanged();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }

    @Override
    public ItemViewCreator<Video> configItemViewCreator() {
        return new ItemViewCreator<Video>() {
            @Override
            public View newContentView(LayoutInflater inflater, ViewGroup parent, int viewType) {
                return inflater.inflate(R.layout.item_video,parent,false);
            }

            @Override
            public IItemView<Video> newItemView(View view, int viewType) {
                return new VideoViewHolder(view);
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ActivityUtils.from(this)
                .to(VideoPlayActivity.class)
                .extra("video_list",(ArrayList<? extends Parcelable>)mList)
                .extra("current",position)
                .go();
    }


}
