package zhouzhou.com.noedge.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

public class ViewAnimation extends Animation {
       int mCenterX;//记录View的中间坐标   
       int mCenterY;
       private Camera mCamera;
       private boolean isZ;
       public ViewAnimation() {   
       }   
    
       @Override  
       public void initialize(int width, int height, int parentWidth, int parentHeight) {   
           super.initialize(width, height, parentWidth, parentHeight);
           mCamera = new Camera();
           //初始化中间坐标值   
           mCenterX = width/2;    
           mCenterY = height/2;   
           setDuration(10000);
           setFillAfter(true);
           setRepeatCount(-1);
           setInterpolator(new LinearInterpolator());
       }
    
       @Override  
       protected void applyTransformation(float interpolatedTime,   
              Transformation t) {
           final Matrix matrix = t.getMatrix();
//           matrix.setScale(interpolatedTime, interpolatedTime);
//           //通过坐标变换，把参考点（0,0）移动到View中间
//           matrix.preTranslate(-mCenterX, -mCenterY);
//           //动画完成后再移回来
//           matrix.postTranslate(mCenterX, mCenterY);
           final float fromDegrees = 0;
           float degrees = fromDegrees + ((359 - fromDegrees) * interpolatedTime);
           System.out.println("interpolatedTime:" + interpolatedTime);
//           mCamera.translate(0.0f, 0.0f, 0 * interpolatedTime);
//           mCamera.translate(0.0f, 0.0f, 310 * (1.0f - interpolatedTime));
           mCamera.save();
           mCamera.translate(0.0f, 0f,0f);
           mCamera.rotateY(degrees);
           mCamera.getMatrix(matrix);
           mCamera.restore();

           matrix.preTranslate(-mCenterX, -mCenterX);
           matrix.postTranslate(mCenterX, mCenterX);
       }   
    }  