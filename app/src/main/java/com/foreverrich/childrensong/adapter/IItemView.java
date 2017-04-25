package com.foreverrich.childrensong.adapter;

import android.view.View;

/**
 * Created by lyf on 2016/8/10.
 */
public interface IItemView<T> {

    void onBindView(View view);

    void onBindData(T bean, int position);
//    int size();

    int position();

    View getConvertView();
}
