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
		//��ȡ��ǰ�ؼ��Ŀ��
		int width = this.getWidth();
		//��ȡ��ǰ�ؼ��ĸ߶�
		int height = this.getHeight();
		//xyΪĿǰԲ�ĵ������
		x = width/2;
		y = height/2;

	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//����һ��Բ
		Paint paint = new Paint();
		//���õ�ǰ���ʵ���ɫ
		paint.setColor(Color.RED);
		canvas.drawCircle(x, y, radius, paint);
	}
	//������С����϶���ʱ����Ҫͨ����������
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
			//�ж���������С������Ļ� ��ô���ڵ����λ�þ���С��Ų������λ�� ��ôֻҪ���µ���onDraw���°���λ�û�С��Ϳ�����
			if(onBall){
				x = event.getX();
				y = event.getY();
				//ˢ���ػ�ķ���
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
	//�жϵ�ǰ����ȥ��λ���Ƿ���Բ������
	private boolean onBall(float downX, float downY) {
		//���ù��ɶ��� �жϵ�ǰ���λ�õ����� ��������ľ���Բ�ĵľ����Ƿ����Բ�İ뾶 �������Բ�İ뾶�Ļ� ��ô���Ǵ��� �������С��
		//�����λ�ü�ȥԭ����λ�� ����������м䵽Բ�ĵ�֮��ľ��� �����ж�
		float sqrt = FloatMath.sqrt((downX-x)*(downX-x)+(downY-y)+(downY-y));
		if(sqrt<=radius){
			return true;
		}
		return false;
	}

}
