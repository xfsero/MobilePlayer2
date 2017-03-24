package com.stupidwind.com.mobileplayer.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.stupidwind.com.mobileplayer.R;

/**
 * Created by 蠢风 on 2017/3/24.
 */

public class SystemVideoPlayer extends Activity{

    private VideoView videoView;

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        videoView = (VideoView) findViewById(R.id.videoview);

        //设置准备好的监听
        videoView.setOnPreparedListener(new MyOnPreparedListener());

        //设置出错了的监听
        videoView.setOnErrorListener(new MyOnErrorListener());

        //设置播放完的监听
        videoView.setOnCompletionListener(new MyOnCompletionListener());

        uri = getIntent().getData();
        if(uri != null) {
            videoView.setVideoURI(uri);
        }
        videoView.setMediaController(new MediaController(this));
    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener {
        @Override
        public void onPrepared(MediaPlayer mp) {
            videoView.start();
        }
    }

    class MyOnErrorListener implements MediaPlayer.OnErrorListener {

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return false;
        }
    }

    class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {

        }
    }

}
