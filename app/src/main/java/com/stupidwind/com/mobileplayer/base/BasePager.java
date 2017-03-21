package com.stupidwind.com.mobileplayer.base;

import android.content.Context;
import android.view.View;

/**
 * Created by 蠢风 on 2017/3/21.
 */

public abstract class BasePager {

    public Context context;

    public View rootView;

    public boolean isInitData;

    public BasePager(Context context) {
        this.context = context;
        rootView = initView();
    }

    //子页面实现该方法
    public abstract View initView();

    //初始化数据
    public void initData() { }

}
