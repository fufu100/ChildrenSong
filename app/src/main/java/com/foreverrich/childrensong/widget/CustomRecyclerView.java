package com.foreverrich.childrensong.widget;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.foreverrich.childrensong.adapter.BaseRecyclerAdapter;


/**
 * Created by lyf on 2016/6/13.
 */
public class CustomRecyclerView extends RecyclerView {
    private LinearLayoutManager mLinearLayoutManager;
    private int lastEndItem;
    private LoadNextPageListener mLoadNextPageListener;
    public CustomRecyclerView(Context context) {
        this(context,null);
    }
    public CustomRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public CustomRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mLinearLayoutManager = new LinearLayoutManager(context);
        setLayoutManager(mLinearLayoutManager);
        addOnScrollListener(mOnScrollListener);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        //如果是网格布局管理器，以下代码可以使加载更多的progressbar水平居中,2017-3-6
        if(layout instanceof GridLayoutManager){
            final GridLayoutManager gridLayoutManager = (GridLayoutManager)layout;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getAdapter().getItemViewType(position) == 0
                            ? gridLayoutManager.getSpanCount() : 1;
                }
            });
        }
        super.setLayoutManager(layout);
    }

    public RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView,
                                         int newState) {
            int lastVisiblePosition = mLinearLayoutManager.findLastCompletelyVisibleItemPosition();
            if (newState == RecyclerView.SCROLL_STATE_IDLE && getAdapter() != null){
                int lastPosition = getAdapter().getItemCount() - 1;
                if(lastVisiblePosition == lastPosition){
                    if(getAdapter() instanceof BaseRecyclerAdapter){
                        BaseRecyclerAdapter adapter = (BaseRecyclerAdapter)getAdapter();
                        if(mLoadNextPageListener != null  && lastVisiblePosition != lastEndItem && adapter.isShowFooter()){
                            mLoadNextPageListener.onLoadNextPage();
                        }
                    }


                }
                lastEndItem = lastVisiblePosition;
            }
        }
    };

    public void setLoadNextPageListener(LoadNextPageListener l){
        this.mLoadNextPageListener = l;
    }
    public interface LoadNextPageListener {
        void onLoadNextPage();
    }
}
