package com.foreverrich.childrensong.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foreverrich.childrensong.R;
import com.foreverrich.childrensong.bean.Video;

import butterknife.Bind;

/**
 * Created by lyf on 2017/5/3.
 */

public class VideoViewHolder extends BaseViewHolder<Video> {
    @Bind(R.id.image)ImageView mImageView;
    @Bind(R.id.text)TextView mTextView;
    public VideoViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBindData(Video bean, int position) {
        Glide.with(getContext())
                .load(bean.image)
                .into(mImageView);
        mTextView.setText(bean.title);
    }
}
