package com.chocolat0w0.whac_a_mole;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Observable;
import java.util.Observer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class HolesView extends View implements Observer {
	
	private Paint[] mPaint = new Paint[MolesController.HOLE_NUMBER];
	private Point windowSize = null;
	private Canvas canvas;
	private Bitmap[] mBitmap = new Bitmap[MolesController.HOLE_NUMBER];
	
	private HoleImage[] holeImage = new HoleImage[MolesController.HOLE_NUMBER];
	
	public HolesView(Context context, MolesController molesController, HoleImage[] holeImages) {
		super(context);
		this.holeImage = holeImages;
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		windowSize = new Point();
		overrideGetSize(disp, windowSize);
		for (int i = 0; i < MolesController.HOLE_NUMBER; i++) {
			mPaint[i] = new Paint();
			mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
			holeImage[i] = new HoleImage(molesController.getMoleAt(i), this, windowSize);
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		this.canvas = canvas;
		for (int y = 0; y < MolesController.HOLE_COLUMN; y++) {
			for (int x = 0; x < MolesController.HOLE_ROW; x++) {
				Bitmap image = holeImage[calcHoleNumber(x,y)].getImage();
				holeImage[calcHoleNumber(x, y)].setHoleArea(x, y, windowSize);
				drawHole(x, y, image);
			}
		}
	}
	
	
	private void drawHole(int x, int y, Bitmap image) {
		int holeNum = calcHoleNumber(x, y);
		canvas.drawBitmap(image,
				holeImage[holeNum].getLeft(),
				holeImage[holeNum].getTop(),
				mPaint[holeNum]);
	}

	private int calcHoleNumber(int x, int y) {
		int holeNum = x * MolesController.HOLE_COLUMN + y;
		return holeNum;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		MolesController molesController = (MolesController) o;
		for (int i = 0; i < MolesController.HOLE_NUMBER; i++) {
			holeImage[i].changeMole(molesController.getMoleAt(i), this, windowSize);
		}
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


	public void popGotPoint(int holeNum, IMole mole) {
		// TODO 子viewをもう一枚つくって操作する？
		Bitmap image = null;
		switch(mole.getType()) {
		case MIDDLE:
			image = BitmapFactory.decodeResource(getResources(), R.drawable.p300);
			break;
		case HIGH:
			image = BitmapFactory.decodeResource(getResources(), R.drawable.p500);
			break;
		case MINUS:
			image = BitmapFactory.decodeResource(getResources(), R.drawable.m600);
			break;
		default:
			image = BitmapFactory.decodeResource(getResources(), R.drawable.p300);
			break;
		}
		
		 canvas.drawBitmap(image,
				holeImage[holeNum].getLeft(),
				holeImage[holeNum].getTop(),
				mPaint[holeNum]);
	}

}
