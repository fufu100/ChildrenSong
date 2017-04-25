package com.foreverrich.childrensong.lib;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.foreverrich.childrensong.R;
import com.foreverrich.childrensong.adapter.BaseRecyclerAdapter;
import com.foreverrich.childrensong.utils.ToastUtils;
import com.foreverrich.childrensong.widget.CustomRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by lyf on 2016/7/3.
 *
 */

public abstract class BaseRecyclerViewActivity<T> extends BaseActivity implements
        IRecyclerView<T>, AdapterView.OnItemClickListener, CustomRecyclerView.LoadNextPageListener, BaseRefreshView<T> {
    public CustomRecyclerView mRecyclerView;
    protected int pageIndex;
    protected List<T> mList = new ArrayList<>();
    private BaseRecyclerAdapter<T> mAdapter;
    @Nullable
    @Bind(R.id.empty)View emptyView;
    @Nullable
    @Bind(R.id.empty_text)
    TextView emptyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
    }

    protected void initView() {
        mRecyclerView = (CustomRecyclerView) findViewById(R.id.recyclerview);
        emptyTv = (TextView) findViewById(R.id.empty_text);
        pageIndex = 1;
        mAdapter = newAdapter();
        configRecyclerView();
        mRecyclerView.setLoadNextPageListener(this);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        if(emptyView != null){
            emptyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(emptyTv != null && emptyTv.getText().toString().equals(getResources().getString(R.string.prompt_net_erro))) {
                        hideEmptyView();
                        requestData();
                    }
                }
            });
        }
    }

    protected abstract void requestData();

    protected void configRecyclerView() {
    }

    public BaseRecyclerAdapter<T> getAdapter() {
        return mAdapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public BaseRecyclerAdapter<T> newAdapter() {
        return new BaseRecyclerAdapter<T>(this, configItemViewCreator(), mList);
    }

    @Override
    public void onLoadNextPage() {
        requestData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mList.clear();
        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(null);
            mAdapter = null;
        }
    }

    protected void onRefreshFinish() {
    }

    @Override
    public void onRefreshSuccess(List<T> list) {
        onRefreshFinish();
        if (pageIndex == 1) {
            mList.clear();
//            mRecyclerView.scrollToPosition(0);
        }
        mList.addAll(list);
        if (list.size() < 20) {
            mAdapter.isShowFooter(false);
        } else {
            mAdapter.isShowFooter(true);
        }
        mAdapter.notifyDataSetChanged();
        pageIndex++;
        if (mList.size() == 0) {
            showEmptyView();
        } else {
            hideEmptyView();
        }
    }

    @Override
    public void onRefreshFail(Exception e) {
        onRefreshFinish();
        if (pageIndex == 1) {
            showNetErroView();
        } else {
            ToastUtils.showSnackBar(mRecyclerView, getResources().getString(R.string.load_fail), R.string.retry, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    requestData();
                }
            });
        }
    }

    protected void showEmptyView() {
        if(emptyView != null && emptyTv != null){
            emptyView.setVisibility(View.VISIBLE);
            emptyTv.setText(getResources().getString(R.string.no_content));
            emptyTv.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        }
    }

    protected void hideEmptyView() {
        if(emptyView != null){
            emptyView.setVisibility(View.GONE);
        }
    }

    protected void showNetErroView() {
        if(emptyView != null && emptyTv != null){
            emptyView.setVisibility(View.VISIBLE);
            emptyTv.setText(getResources().getString(R.string.prompt_net_erro));
            emptyTv.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.ic_error_outline_black_24dp,0,0);
        }
    }
}
