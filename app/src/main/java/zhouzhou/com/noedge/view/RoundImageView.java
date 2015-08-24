package zhouzhou.com.noedge.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import zhouzhou.com.noedge.R;

/**
 * Created by zhouzhou on 15/8/23.
 */
public class RoundImageView extends ImageView {

    private Bitmap bitmap ;
    private Matrix matrix ; //作用矩阵
    private Camera camera ;
    private int deltaX= 0 , deltaY=0 ; //翻转角度差值
    private int centerX , centerY ;
    private Context context;//图片中心点
    public RoundImageView(Context context){
        super(context);
        this.context = context;
        initData();
    }
    public RoundImageView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
        initData();
    }

    public RoundImageView(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
        this.context = context;
        initData();
    }

    private void initData(){
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.app_image);
//        BitmapDrawable bd = (BitmapDrawable)this.getDrawable();
//        if(bd != null){
//            bitmap = bd.getBitmap();
//        }
        centerX = bitmap.getWidth()/2 ;
        centerY = bitmap.getHeight()/2 ;
        matrix = new Matrix();
        camera = new Camera();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        camera.save();
        //绕X轴翻转
        camera.rotateX(-deltaY);
        //绕Y轴翻转
        camera.rotateY(deltaX);
        //设置camera作用矩阵
        camera.getMatrix(matrix);
        camera.restore();
        //设置翻转中心点
        matrix.preTranslate(-this.centerX, -this.centerY);
        matrix.postTranslate(this.centerX, this.centerY);
        canvas.drawBitmap(bitmap, matrix, null);
    }
    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
        invalidate();
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
        invalidate();
    }
}
