package com.foreverrich.childrensong.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lyf on 2016/8/10.
 */
public interface ItemViewCreator<T> {
    View newContentView(LayoutInflater inflater, ViewGroup parent, int viewType);

    IItemView<T>  newItemView(View view, int viewType);
}
