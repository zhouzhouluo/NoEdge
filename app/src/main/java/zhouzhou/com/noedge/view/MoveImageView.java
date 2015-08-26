package zhouzhou.com.noedge.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MoveImageView extends ImageView {
    public MoveImageView(Context context) {
        super(context);
    }
    public MoveImageView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }
    public MoveImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public void setLocation(int x, int y) {
        this.setFrame(x, y - this.getHeight(), x + this.getWidth(), y);
    }
    // 移动
    public boolean autoMouse(MotionEvent event) {
        boolean rb = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                this.setLocation((int) event.getX(), (int) event.getY());
                rb = true;
                break;
        }
        return rb;
    }
}