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
	private static final int HOLE_NUMBER = 4;
	
	private Paint[] mPaint = new Paint[HOLE_NUMBER];
	private float radius = 30;
	private Point size = null;
	private Canvas canvas;
	
	public HoleView(Context context) {
		super(context);
		for (int i = 0; i < HOLE_NUMBER; i++) {
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
		for (int i = 0; i < HOLE_NUMBER; i++) {
			drawHole(i);
		}
	}
	
	private void drawHole(int i) {
		switch (i) {
		case 0 :
			canvas.drawCircle(size.x / 3, size.y / 3, radius, mPaint[i]);
			break;
		case 1 :
			canvas.drawCircle(size.x / 3 * 2, size.y / 3, radius, mPaint[i]);
			break;
		case 2 :
			canvas.drawCircle(size.x / 3, size.y / 3 * 2, radius, mPaint[i]);
			break;
		case 3 :
			canvas.drawCircle(size.x / 3 * 2, size.y / 3 * 2, radius, mPaint[i]);
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

}
