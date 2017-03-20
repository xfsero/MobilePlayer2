package com.stupidwind.com.mobileplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;


public class SplashActivity extends Activity {

    private static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行主线程
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
            finish();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TAG, "当前事件名称 = Action" + event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }
}
