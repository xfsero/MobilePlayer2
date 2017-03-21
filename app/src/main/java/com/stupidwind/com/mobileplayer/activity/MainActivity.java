package com.stupidwind.com.mobileplayer.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.stupidwind.com.mobileplayer.R;
import com.stupidwind.com.mobileplayer.base.BasePager;
import com.stupidwind.com.mobileplayer.activity.pager.*;
import com.stupidwind.com.mobileplayer.pager.AudioPager;
import com.stupidwind.com.mobileplayer.pager.NetAudioPager;
import com.stupidwind.com.mobileplayer.pager.NetVideoPager;
import com.stupidwind.com.mobileplayer.pager.VideoPager;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private RadioGroup rg_bottom_tap;

    private ArrayList<BasePager> basePagers;

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_bottom_tap = (RadioGroup) findViewById(R.id.rg_bottom_tag);

        basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        //设置RadioGroupd的监听
        rg_bottom_tap.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_bottom_tap.check(R.id.rb_video);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId)
            {
                case R.id.rb_video:
                    position = 0;
                    break;
                case R.id.rb_audio:
                    position = 1;
                    break;
                case R.id.rb_net_video:
                    position = 2;
                    break;
                case R.id.rb_net_audio:
                    position = 3;
                    break;
                default:
            }

            setFragment();

        }
    }

    private void setFragment() {
        //得到FragmentManager
        FragmentManager manager = getFragmentManager();
        //开启事务
        FragmentTransaction ft = manager.beginTransaction();
        //替换
        ft.replace(R.id.fl_main_content, new Fragment() {
            @Nullable
            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                BasePager basePager = getBasePager();
                if (basePager != null) {
                    //返回子页面的视图
                    return basePager.rootView;
                }
                return null;
            }
        });

        //提交事务
        ft.commit();
    }

    private BasePager getBasePager()
    {
        BasePager basePager = basePagers.get(position);
        if(basePager != null && !basePager.isInitData)
        {
            basePager.initData();
            basePager.isInitData = true;
        }
        return basePager;
    }

}
