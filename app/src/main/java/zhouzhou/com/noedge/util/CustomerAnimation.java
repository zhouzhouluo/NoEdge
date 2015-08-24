package zhouzhou.com.noedge.util;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CustomerAnimation extends Animation {
    private int mWaveTimes=1;//摇摆次数
    private int mWaveRange=50;//摇摆幅度

    public CustomerAnimation(){

    }

    public CustomerAnimation(int waveTimes,int waveRange){
        mWaveTimes = waveTimes;
        mWaveRange = waveRange;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        //运用周期性函数，实现左右摇摆
        t.getMatrix().setTranslate((int)(Math.sin(interpolatedTime*Math.PI*mWaveTimes)*mWaveRange),0);
    }
}