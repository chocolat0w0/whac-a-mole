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
	private final float radius = 50;
	private Point windowSize = null;
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
		windowSize = new Point();
		overrideGetSize(disp, windowSize);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		this.canvas = canvas;
		for (int j = 0; j < MoleController.HOLE_COLUMN; j++) {
			for (int i = 0; i < MoleController.HOLE_ROW; i++) {
				setHoleArea(i, j);
				drawHole(i, j);
			}
		}
	}
	
	private void setHoleArea(int x, int y) {
		int holeNum = x * MoleController.HOLE_COLUMN + y;
		if(holeArea[holeNum] == null) {
			holeArea[holeNum] = new HoleArea();
		}
		holeArea[holeNum].setCirclePosition(windowSize.x / (MoleController.HOLE_ROW+1) * (x+1),
				windowSize.y / (MoleController.HOLE_COLUMN+1) * (y+1),
				radius);
		
	}
	
	private void drawHole(int x, int y) {
		int holeNum = x * MoleController.HOLE_COLUMN + y;
		canvas.drawCircle(windowSize.x / (MoleController.HOLE_ROW+1) * (x+1),
				windowSize.y / (MoleController.HOLE_COLUMN+1) * (y+1),
				radius, mPaint[holeNum]);
	}

	@SuppressWarnings({ "deprecation", "rawtypes" })
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
