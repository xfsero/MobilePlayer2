package com.stupidwind.com.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class SplashActivity extends Activity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程中
                Log.d(TAG, "当前线程名称 = " + Thread.currentThread().getName());
                startMainActivity();
            }
        }, 2000);

    }

    private boolean isStartMain = false;

    //跳转到主页面
    private void startMainActivity() {

        if(!isStartMain)
        {
            isStartMain = true;
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            //关闭当前Activity
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "当前事件名称 = Action" + event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        //把所有消息和回调移除
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
