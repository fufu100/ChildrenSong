package com.foreverrich.childrensong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.foreverrich.childrensong.adapter.ItemViewCreator;
import com.foreverrich.childrensong.bean.VideoCategory;
import com.foreverrich.childrensong.lib.BaseRecyclerViewActivity;

import butterknife.Bind;

/**
 * Created by lyf on 2017/4/22.
 */

public class MainActivity extends BaseRecyclerViewActivity<VideoCategory> {
    @Bind(R.id.recyclerview)RecyclerView mRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public ItemViewCreator<VideoCategory> configItemViewCreator() {
        return null;
    }
}
