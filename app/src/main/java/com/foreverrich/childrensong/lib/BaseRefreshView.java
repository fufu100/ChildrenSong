package com.foreverrich.childrensong.lib;

import java.util.List;

/**
 * Created by lyf on 2016/8/19.
 */

public interface BaseRefreshView<T>  extends BaseView{
    void onRefreshSuccess(List<T> list);
    void onRefreshFail(Exception e);

}


