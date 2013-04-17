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
	
	private Paint[] mPaint = new Paint[Holes.HOLE_NUMBER];
	private Point windowSize = null;
	private Canvas canvas;
	private HoleArea[] holeArea = new HoleArea[Holes.HOLE_NUMBER];
	private Bitmap[] mBitmap = new Bitmap[Holes.HOLE_NUMBER];
	
	private HoleImage[] holeView = new HoleImage[Holes.HOLE_NUMBER];
	
	public HolesView(Context context, Holes holes) {
		super(context);
		for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
			mPaint[i] = new Paint();
			mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
			holeView[i] = new HoleImage(i, holes.getMoleAt(i));
		}
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		windowSize = new Point();
		overrideGetSize(disp, windowSize);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		this.canvas = canvas;
		for (int y = 0; y < Holes.HOLE_COLUMN; y++) {
			for (int x = 0; x < Holes.HOLE_ROW; x++) {
				// 
				Bitmap image = holeView[calcHoleNumber(x,y)].getImage(this);
				adjustImageSize(x, y, image);
				setHoleArea(x, y, image);
				drawHole(x, y, image);
			}
		}
	}
	
	private void adjustImageSize(int x, int y, Bitmap image) {
		int holeNum = calcHoleNumber(x, y);
		if(windowSize.x / Holes.HOLE_COLUMN < image.getWidth()) {
			final int resizedWidth = windowSize.x / (Holes.HOLE_COLUMN+1) - 10;
			image = Bitmap.createScaledBitmap(image, 
					resizedWidth, 
					image.getHeight() * resizedWidth / image.getWidth(), 
					true);
		}
	}

	private void setHoleArea(int x, int y, Bitmap image) {
		int holeNum = calcHoleNumber(x, y);
		if(holeArea[holeNum] == null) {
			holeArea[holeNum] = new HoleArea();
		}
		holeArea[holeNum].setImageArea(windowSize.x / (Holes.HOLE_ROW+1) * (x+1),
				windowSize.y / (Holes.HOLE_COLUMN+1) * (y+1), 
				image);
	}
	
	private void drawHole(int x, int y, Bitmap image) {
		int holeNum = calcHoleNumber(x, y);
		canvas.drawBitmap(image,
				holeArea[holeNum].left,
				holeArea[holeNum].top,
				mPaint[holeNum]);
	}

	private int calcHoleNumber(int x, int y) {
		int holeNum = x * Holes.HOLE_COLUMN + y;
		return holeNum;
	}
	
	public boolean isExisted(float x, float y) {
		for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public int getTouchedHoleNum(float x, float y) {
		for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return i;
			}
		}
		// TODO: error処理
		return 0;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Holes holes = (Holes) o;
		GameStatusView.debugInfo("ここきた？");
		for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
			holeView[i].changeMole(holes.getMoleAt(i));
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

	class HoleArea {
		private float left;
		private float right;
		private float top;
		private float bottom;
		
		public void setImageArea(int centerX, int centerY, Bitmap image) {
			left = centerX - image.getWidth() / 2;
			right = centerX + image.getWidth() / 2;
			top = centerY - image.getHeight() / 2;
			bottom = centerY + image.getHeight() / 2;
		}
		
		public boolean isContain(float x, float y) {
			if (left <= x && x <= right && top <= y && y <= bottom ) {
				return true;
			}
			return false;
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
				holeArea[holeNum].left,
				holeArea[holeNum].top,
				mPaint[holeNum]);
		
		
	}

}
