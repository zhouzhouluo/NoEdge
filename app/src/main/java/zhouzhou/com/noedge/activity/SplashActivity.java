package zhouzhou.com.noedge.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import zhouzhou.com.noedge.R;
import zhouzhou.com.noedge.animation.CustomerAnimation;
import zhouzhou.com.noedge.animation.PostScaleAnimation;
import zhouzhou.com.noedge.animation.ViewAnimation;
import zhouzhou.com.noedge.view.FlipImgEffectView;
import zhouzhou.com.noedge.view.MoveImageView;
import zhouzhou.com.noedge.view.RoundImageView;

public class SplashActivity extends Activity {
    private ViewGroup _root;
    private TextView appName_TextView;
    private MoveImageView appimageview;
    private RoundImageView roundImageView;
    private int _xDelta;
    private int _yDelta;
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


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            appName_TextView.setText("ppppppp");
            appimageview.setImageResource(R.mipmap.ic_launcher);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        FlipImgEffectView myView = new FlipImgEffectView(this);
//        setContentView(myView);
        setContentView(R.layout.activity_splash);
        _root = (ViewGroup) findViewById(R.id.root);
        appName_TextView = (TextView)findViewById(R.id.appName_TextView);
        appimageview = (MoveImageView)findViewById(R.id.appImageView);
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.setDuration(3000);
        TranslateAnimation translateAnimation = new TranslateAnimation(-500f,0f,0f,0);
        translateAnimation.setStartOffset(1000);
        animationSet.addAnimation(translateAnimation);
//        AlphaAnimation alphaAnimation = new AlphaAnimation(0f,1.0f);
//        alphaAnimation.setRepeatMode(Animation.REVERSE);
//        animationSet.addAnimation(alphaAnimation);
//        PostScaleAnimation postScaleAnimation = new PostScaleAnimation(-500f,0,-1,-1);
//        postScaleAnimation.setStartOffset(1000);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1,Animation.RELATIVE_TO_SELF, -0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setStartOffset(1000);
        animationSet.addAnimation(scaleAnimation);
        appimageview.startAnimation(animationSet);
        CustomerAnimation customerAnimation = new CustomerAnimation();
        customerAnimation.setRepeatCount(-1);
//        customerAnimation.setDuration(3000);
//        animationSet.addAnimation(customerAnimation);
        ViewAnimation viewAnimation = new ViewAnimation();
        viewAnimation.setRepeatCount(-1);
//        animationSet.addAnimation(viewAnimation);
//        appimageview.startAnimation(customerAnimation);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//19.7 152
        rotateAnimation.setFillAfter(true);
//        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(-1);
//        animationSet.addAnimation(rotateAnimation);
//        appimageview.startAnimation(animationSet);
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
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    Thread.sleep(10000);
//                    Message msg = myHandler.obtainMessage(1);
//                    myHandler.sendMessage(msg);
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            appName_TextView.setText("ppppppp");
//                            appimageview.setImageResource(R.mipmap.ic_launcher);
//                        }
//                    },10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        appimageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int X = (int)event.getRawX();
                final int Y = (int)event.getRawY();
//                String mytag = v.getTag().toString();
//                Log.d("zzz","mytag:"+mytag);
//                Log.d("zzz","");
                Log.d("zzz","X:"+X);
                Log.d("zzz","Y:"+Y);
//                switch (event.getAction()&MotionEvent.ACTION_MASK){
//                    case MotionEvent.ACTION_DOWN:
//                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) v
//                                .getLayoutParams();
//                        _xDelta = X - lParams.leftMargin;
//                        _yDelta = Y - lParams.topMargin;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        break;
//                    case MotionEvent.ACTION_POINTER_UP:
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
//                                .getLayoutParams();
//                        layoutParams.leftMargin = X - _xDelta;
//                        layoutParams.topMargin = Y - _yDelta;
//                        layoutParams.rightMargin = -250;
//                        layoutParams.bottomMargin = -250;
//                        v.setLayoutParams(layoutParams);
//                        break;
//                }
//                _root.invalidate();
                ((MoveImageView)v).autoMouse(event);
                return false;
            }
        });
    }
//    private void moveViewWithFinger(View view, float rawX, float rawY) {
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view
//                .getLayoutParams();
//        params.leftMargin = (int) rawX - ivMove.getWidth() / 2;
//        params.topMargin = (int) rawY - topTitleHeight - ivMove.getHeight() / 2;
//        view.setLayoutParams(params);
//    }

}
