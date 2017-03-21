package com.stupidwind.com.mobileplayer.pager;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.stupidwind.com.mobileplayer.base.BasePager;

/**
 * Created by 蠢风 on 2017/3/21.
 */

public class AudioPager extends BasePager {

    TextView textView;

    public AudioPager(Context context) {
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
        Log.d("AudioPager", "本地音乐的数据初始化了...");
        textView.setText("本地音乐页面");
    }
}
