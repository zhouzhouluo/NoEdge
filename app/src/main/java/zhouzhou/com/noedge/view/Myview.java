

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
    private  oolean isScale = false;

    //构造方法
    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        //获得位图
        bm = BitmapFactory.decodeResource(this.getResources(),
                R.drawable.girl);
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
    public  oolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
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