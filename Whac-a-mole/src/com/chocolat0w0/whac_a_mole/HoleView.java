package com.chocolat0w0.whac_a_mole;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class HoleView extends View {
	
	private Paint[] mPaint = new Paint[MoleController.HOLE_NUMBER];
	private float radius = 30;
	private Point size = null;
	private Canvas canvas;
	private HoleArea[] holeArea = new HoleArea[MoleController.HOLE_NUMBER];
	
	
	public HoleView(Context context) {
		super(context);
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			mPaint[i] = new Paint();
			mPaint[i].setColor(Color.GRAY);
			mPaint[i].setAntiAlias(true);
		}
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		size = new Point();
		overrideGetSize(disp, size);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		this.canvas = canvas;
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			drawHole(i);
		}
	}
	
	private void drawHole(int i) {
		holeArea[i] = new HoleArea();
		switch (i) {
		case 0 :
			canvas.drawCircle(size.x / 3, size.y / 3, radius, mPaint[i]);
			holeArea[i].setCirclePosition(size.x / 3, size.y / 3, radius);
			break;
		case 1 :
			canvas.drawCircle(size.x / 3 * 2, size.y / 3, radius, mPaint[i]);
			holeArea[i].setCirclePosition(size.x / 3 * 2, size.y / 3, radius);
			break;
		case 2 :
			canvas.drawCircle(size.x / 3, size.y / 3 * 2, radius, mPaint[i]);
			holeArea[i].setCirclePosition(size.x / 3, size.y / 3 * 2, radius);
			break;
		case 3 :
			canvas.drawCircle(size.x / 3 * 2, size.y / 3 * 2, radius, mPaint[i]);
			holeArea[i].setCirclePosition(size.x / 3 * 2, size.y / 3 * 2, radius);
			break;
		default :
			break;
		
		}
	}

	void overrideGetSize(Display display, Point outSize) {
	    try {
	      // test for new method to trigger exception
	      Class pointClass = Class.forName("android.graphics.Point");
	      Method newGetSize = Display.class.getMethod("getSize", new Class[]{ pointClass });

	      // no exception, so new method is available, just use it
	      newGetSize.invoke(display, outSize);
	    } catch(NoSuchMethodException ex) {
	      // new method is not available, use the old ones
	      outSize.x = display.getWidth();
	      outSize.y = display.getHeight();
	    } catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void addMole(int i) {
		mPaint[i].setColor(Color.RED);
	}

	public boolean isExisted(float x, float y) {
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	class HoleArea {
		private float left;
		private float right;
		private float top;
		private float bottom;
		
		public void setCirclePosition(int centerX, int centerY, float radius) {
			left = centerX - radius;
			right = centerX + radius;
			top = centerY - radius;
			bottom = centerY + radius;
		}
		
		public boolean isContain(float x, float y) {
			if (left <= x && x <= right && top <= y && y <= bottom ) {
				return true;
			}
			return false;
		}
	}

	public int touchHoleNum(float x, float y) {
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return i;
			}
		}
		// TODO: error処理
		return 0;
	}

	public void removeMole(int holeNum) {
		// TODO 自動生成されたメソッド・スタブ
		mPaint[holeNum].setColor(Color.GRAY);
	}

}
