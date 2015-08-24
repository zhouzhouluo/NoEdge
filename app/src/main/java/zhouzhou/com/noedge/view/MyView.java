package zhouzhou.com.noedge.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import zhouzhou.com.noedge.R;

//自定义视图类
public class MyView extends View{
    //位图实例
    private Bitmap bm;
    //Matrix实例
    private Matrix matrix = new Matrix();
    //旋转角度
    private float angle = 0.0f;
    //位图的宽和高
    private int w,h;
    //缩放比例
    private float scale = 1.0f;
    //判断缩放还是旋转
    private  boolean isScale = false;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        //获得位图
        bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.app_image);
        //获得位图宽
        w = bm.getWidth();
        //获得位图高
        h = bm.getHeight();
        //使当前视图获得焦点
        this.setFocusable(true);
    }

    //构造方法
    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        //获得位图
        bm = BitmapFactory.decodeResource(this.getResources(), R.mipmap.app_image);
        //获得位图宽
        w = bm.getWidth();
        //获得位图高
        h = bm.getHeight();
        //使当前视图获得焦点
        this.setFocusable(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //重置Matrix
        matrix.reset();
        if(!isScale){
            //旋转Matrix
            matrix.setRotate(angle);
        }else{
            //缩放Matrix
            matrix.setScale(scale, scale);
        }
        //根据原始位图和Matrix创建新视图
        Bitmap bm2 = Bitmap.createBitmap(bm, 0, 0, w, h,matrix, true);
        //绘制新视图
        canvas.drawBitmap(bm2, matrix, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        switch (eventaction){
            case MotionEvent.ACTION_DOWN:
                isScale = false;
                angle++;
                postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                isScale = true;
                if(scale > 0.5)
                    scale -= 0.1;
                postInvalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                isScale = false;
                angle++;
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                isScale = false;
                angle--;
                postInvalidate();
                scale -= 0.1;
                postInvalidate();
                break;

        }
        return super.onTouchEvent(event);
    }

    @Override
    public  boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        Log.d("zzz", "keCode:" + keyCode);
        Log.d("zzz","event:"+event);
        //向左旋转
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
            isScale = false;
            angle++;
            postInvalidate();
        }
        //向右旋转
        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
            isScale = false;
            angle--;
            postInvalidate();
        }
        //放大
        if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
            isScale =true;
            if(scale < 2.0)
                scale += 0.1;
            postInvalidate();
        }
        //缩小
        if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
            isScale = true;
            if(scale > 0.5)
                scale -= 0.1;
            postInvalidate();
        }
        return super.onKeyDown(keyCode, event);
    }
}