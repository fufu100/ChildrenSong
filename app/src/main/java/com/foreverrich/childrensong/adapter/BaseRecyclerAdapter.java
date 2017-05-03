package com.foreverrich.childrensong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.foreverrich.childrensong.R;
import com.foreverrich.childrensong.utils.LogUtils;

import java.util.List;

/**
 * Created by lyf on 2016/8/10.
 */
public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String TAG = "BaseRecyclerAdapter";
    protected final int TYPE_FOOTER = 0;
    protected final int TYPE_HEADER = 2;
    protected final int TYPE_1 = 1;
    protected Context mContext;
    protected ItemViewCreator<T> mItemViewCreator;
    protected List<T> mList;
    protected boolean mShowLoadingFooter = true,mShowFooterItem = true;
    protected View headView;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    public BaseRecyclerAdapter(Context context, ItemViewCreator<T> creator, List<T> list) {
        this.mContext = context;
        this.mItemViewCreator = creator;
        this.mList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (headView != null && position == 0) {
            return TYPE_HEADER;
        } else if ( mShowFooterItem && position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_1;
        }
    }

    @Override
    public int getItemCount() {
        int begin = headView == null ? 0 : 1;
        int end = mShowFooterItem ? 1 : 0;
        return begin + mList.size() + end;
    }

    public void addHeadView(View view) {
        this.headView = view;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView;
        IItemView itemView;
        if (viewType == TYPE_HEADER) {
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            headView.setLayoutParams(lp);
            return new HeadViewHolder(headView);
        } else if (viewType == TYPE_FOOTER) {
            return new FooterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_comm_footer, parent, false));
        } else {
            convertView = mItemViewCreator.newContentView(LayoutInflater.from(mContext), parent, viewType);
            if (convertView == null) {
                LogUtils.DebugLog("convertView is null " + viewType);
            }
            itemView = mItemViewCreator.newItemView(convertView, viewType);
            convertView.setTag(R.id.itemview, itemView);
            itemView.onBindView(convertView);

            return (BaseViewHolder) itemView;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            BaseViewHolder h = (BaseViewHolder) holder;
            int realPosition = headView == null ? position : position - 1;
            h.setPosition(realPosition);
            h.setOnClickListener(mItenClickListener);
            h.onBindData(mList.get(realPosition), position);

            if (mOnItemClickListener != null) {
                h.itemView.setOnClickListener(mItenClickListener);
            } else {
                h.itemView.setOnClickListener(null);
            }

        }else if(holder instanceof FooterViewHolder){
            if(!mShowLoadingFooter) {
                FooterViewHolder h = (FooterViewHolder) holder;
                h.progress.setVisibility(View.GONE);
                h.loading.setVisibility(View.GONE);
                h.emptyView.setVisibility(View.VISIBLE);
            }
        }
    }

    //是否显示加载中或没数据了的条目，默认显示
    public void isShowFooterItem(boolean showFooterItem){
        this.mShowFooterItem = showFooterItem;
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowLoadingFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowLoadingFooter;
    }

    private class HeadViewHolder extends RecyclerView.ViewHolder{

        private HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    private static class FooterViewHolder extends RecyclerView.ViewHolder {
        View emptyView;
        View progress;
        TextView loading;
        private FooterViewHolder(View view) {
            super(view);
            emptyView = view.findViewById(R.id.empty);
            progress = view.findViewById(R.id.progress_wheel);
            loading = (TextView) view.findViewById(R.id.more_data_msg);
        }
    }

    private View.OnClickListener mItenClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            IItemView itemView = (IItemView) v.getTag(R.id.itemview);
            if (itemView != null) {
                mOnItemClickListener.onItemClick(null, itemView.getConvertView(), itemView.position(), getItemId(itemView.position()));
            } else {
                IItemView subItemView = (IItemView) v.getTag(R.id.sub_itemview);
                mOnItemClickListener.onItemClick(null, v, subItemView.position(), v.getId());
            }
        }
    };
}
