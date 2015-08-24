package zhouzhou.com.noedge.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import zhouzhou.com.noedge.R;
import zhouzhou.com.noedge.util.CustomerAnimation;
import zhouzhou.com.noedge.util.Rotate3dAnimation;
import zhouzhou.com.noedge.util.ViewAnimation;
import zhouzhou.com.noedge.view.FlipImgEffectView;
import zhouzhou.com.noedge.view.RoundImageView;

public class SplashActivity extends Activity {
    private TextView appName_TextView;
    private RoundImageView roundImageView;
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
                case 2:
                    Integer i = (Integer)msg.obj;
                    theActivity.roundImageView.setDeltaX(i);
                    break;
            }
        }
    }

    MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FlipImgEffectView myView = new FlipImgEffectView(this);
//        setContentView(myView);
        setContentView(R.layout.activity_splash);
        appName_TextView = (TextView)findViewById(R.id.appName_TextView);
        ImageView appimageview = (ImageView)findViewById(R.id.appImageView);
        CustomerAnimation customerAnimation = new CustomerAnimation();
        customerAnimation.setRepeatCount(-1);
        customerAnimation.setDuration(3000);
        appimageview.startAnimation(customerAnimation);
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//19.7 152
//        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setDuration(2000);
//        rotateAnimation.setRepeatCount(-1);
//        appimageview.startAnimation(rotateAnimation);
        float centerX = appimageview.getWidth() / 2f;
        float centerY = appimageview.getHeight() / 2f;
//        Rotate3dAnimation rotation = new Rotate3dAnimation(0, 359, centerX, centerY,310.0f, true);
//         //动画完成后保持完成的状态
//        rotation.setFillAfter(true);
//        rotation.setDuration(3000);
//        rotation.setRepeatCount(-1);
//        rotation.setInterpolator(new AccelerateInterpolator());
//        appimageview.startAnimation(rotation);
//        ViewAnimation viewAnimation = new ViewAnimation();
//        appimageview.startAnimation(viewAnimation);
//        roundImageView = (RoundImageView)findViewById(R.id.appImage);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    while (true){
//                        for(int i = 0;i<360 ; i++){
//                            Thread.sleep(100);
//                            Message msg = myHandler.obtainMessage(2);
//                            msg.obj = i;
//                            myHandler.sendMessage(msg);
//                        }
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(appName_TextView, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(appName_TextView, "rotation", 0f, 360f);
        rotate.setRepeatCount(-1);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(appName_TextView, "alpha", 1f, 0f, 1f);
        fadeInOut.setRepeatCount(-1);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(10000);
//                    Message msg = myHandler.obtainMessage(1);
//                    myHandler.sendMessage(msg);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}
