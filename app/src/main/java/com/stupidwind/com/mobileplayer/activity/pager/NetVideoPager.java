package com.stupidwind.com.mobileplayer.activity.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.stupidwind.com.mobileplayer.activity.base.BasePager;

/**
 * Created by 蠢风 on 2017/3/21.
 */

public class NetVideoPager extends BasePager {

    TextView textView;

    public NetVideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setTextSize(24);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void initData() {
        Log.d("NetVideoPager", "网络视频的数据初始化了...");
        textView.setText("网络视频页面");
    }
}
