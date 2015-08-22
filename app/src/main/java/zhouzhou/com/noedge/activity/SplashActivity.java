package zhouzhou.com.noedge.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

import zhouzhou.com.noedge.R;

public class SplashActivity extends Activity {

    private static class MyHandler extends Handler {
        WeakReference<SplashActivity> mActivity;
        MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<SplashActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity theActivity = mActivity.get();
            switch (msg.what) {
                case 1:
                    Intent intent = new Intent();
                    intent.setClass(theActivity,MainActivity.class);
                    theActivity.startActivity(intent);
                    theActivity.finish();
                    break;
            }
        }
    }

    MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Message msg = myHandler.obtainMessage(1);
                    myHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
