package com.foreverrich.childrensong.lib;


import com.foreverrich.childrensong.adapter.BaseRecyclerAdapter;
import com.foreverrich.childrensong.adapter.ItemViewCreator;

/**
 * Created by lyf on 2016/8/10.
 */
public interface IRecyclerView<T> {
    BaseRecyclerAdapter<T> newAdapter();
    ItemViewCreator<T> configItemViewCreator();
}
