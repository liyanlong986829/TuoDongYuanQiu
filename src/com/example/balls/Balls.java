package com.example.balls;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Balls extends View {

	private float y;
	private float x;
	private float radius = 80;
	 
	
	private boolean onBall;
	public Balls(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}
	public Balls(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	public Balls(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取当前控件的宽度
		int width = this.getWidth();
		//获取当前控件的高度
		int height = this.getHeight();
		//xy为目前圆心的坐标点
		x = width/2;
		y = height/2;

	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//画出一个圆
		Paint paint = new Paint();
		//设置当前画笔的颜色
		paint.setColor(Color.RED);
		canvas.drawCircle(x, y, radius, paint);
	}
	//当处理小球的拖动的时候需要通过触摸监听
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			float downX = event.getX();
			float downY = event.getY();
			onBall = onBall(downX,downY);
			Toast.makeText(getContext(), ""+onBall, 0).show();
			break;
		case MotionEvent.ACTION_MOVE:
			//判断如果点击到小球上面的话 那么现在点击的位置就是小球挪动到的位置 那么只要重新调用onDraw重新按照位置画小球就可以了
			if(onBall){
				x = event.getX();
				y = event.getY();
				//刷新重绘的方法
				postInvalidate();
			}
			break;
		case MotionEvent.ACTION_UP:

			break;
		case MotionEvent.ACTION_CANCEL:

			break;
		}
		return true;
	}
	//判断当前按下去的位置是否在圆的里面
	private boolean onBall(float downX, float downY) {
		//运用勾股定理 判断当前点的位置的坐标 计算出来的距离圆心的距离是否大于圆的半径 如果大于圆的半径的话 那么就是大于 否则就是小鱼
		//点击的位置减去原来的位置 算出来的是中间到圆心点之间的距离 进行判断
		float sqrt = FloatMath.sqrt((downX-x)*(downX-x)+(downY-y)+(downY-y));
		if(sqrt<=radius){
			return true;
		}
		return false;
	}

}
