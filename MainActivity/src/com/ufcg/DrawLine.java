package com.ufcg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawLine extends View {
	Paint paint = new Paint();
	private int y2;
	private int x2;
	private int y1;
	private int x1;

	public DrawLine(Context context,int x1,int y1,int x2, int y2) {
		super(context);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		
		paint.setColor(Color.BLACK);
	}
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawLine(x1, y1, x2, y2, paint);
	}

}