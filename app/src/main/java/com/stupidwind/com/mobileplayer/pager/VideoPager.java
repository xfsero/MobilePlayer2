package com.stupidwind.com.mobileplayer.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.stupidwind.com.mobileplayer.R;
import com.stupidwind.com.mobileplayer.activity.SystemVideoPlayer;
import com.stupidwind.com.mobileplayer.adapter.VideoPagerAdapter;
import com.stupidwind.com.mobileplayer.base.BasePager;
import com.stupidwind.com.mobileplayer.domain.MediaItem;

import java.util.ArrayList;

/**
 * Created by 蠢风 on 2017/3/21.
 */

public class VideoPager extends BasePager {

    private ListView listView;

    private TextView tv_no_media;

    private ProgressBar pb_loading;

    private VideoPagerAdapter adapter;

    private ArrayList<MediaItem> mediaItems;

    public VideoPager(Context context) {
        super(context);
    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mediaItems != null && mediaItems.size() > 0) {
                //如果mediaItems有数据，给listView设置适配器
                adapter = new VideoPagerAdapter(context, mediaItems);
                listView.setAdapter(adapter);
                //文本隐藏
                tv_no_media.setVisibility(View.GONE);
            } else {
                tv_no_media.setVisibility(View.VISIBLE);
            }

            //隐藏ProgressBar
            pb_loading.setVisibility(View.GONE);

        }
    };

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.video_pager, null);
        listView = (ListView) view.findViewById(R.id.listview);
        tv_no_media = (TextView) view.findViewById(R.id.tv_no_media);
        pb_loading = (ProgressBar) view.findViewById(R.id.pb_loading);

        //设置ListView的Item点击事件
        listView.setOnItemClickListener(new MyOnItemClickListener());

        return view;
    }



    class MyOnItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MediaItem mediaItem = mediaItems.get(position);

            //打开播放器
            Intent intent = new Intent(context, SystemVideoPlayer.class);
            intent.setDataAndType(Uri.parse(mediaItem.getData()), "Video/*");
            context.startActivity(intent);
        }
    }

    @Override
    public void initData()
    {
        Log.d("VideoPager", "本地视频的数据被初始化了");
        //加载本地视频数据
        getDataFromLocal();
    }

    private void getDataFromLocal() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                mediaItems = new ArrayList<>();
                ContentResolver resolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,    //视频的名字
                        MediaStore.Video.Media.DURATION,        //视频的总时长
                        MediaStore.Video.Media.SIZE,            //视频的大小
                        MediaStore.Video.Media.DATA,            //视频的绝对地址
                        MediaStore.Video.Media.ARTIST           //视频的艺术家
                };

                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if(cursor != null) {
                    while(cursor.moveToNext())
                    {
                        MediaItem mediaItem = new MediaItem();
                        mediaItems.add(mediaItem);

                        String name = cursor.getString(0);
                        mediaItem.setName(name);

                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);

                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);

                        String data = cursor.getString(3);
                        mediaItem.setData(data);


                        String artist = cursor.getString(4);
                        mediaItem.setArtist(artist);
                    }
                    cursor.close();
                }

                handler.sendEmptyMessage(10);

            }
        }).start();

    }

}
