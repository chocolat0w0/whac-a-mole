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

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

@SuppressLint("NewApi")
public class HoleView extends View implements Observer {
	
	private Paint[] mPaint = new Paint[MoleController.HOLE_NUMBER];
	private Point windowSize = null;
	private Canvas canvas;
	private HoleArea[] holeArea = new HoleArea[MoleController.HOLE_NUMBER];
	
	private Bitmap[] mBitmap = new Bitmap[MoleController.HOLE_NUMBER];
	
	public HoleView(Context context) {
		super(context);
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			mPaint[i] = new Paint();
			mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
		}
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		windowSize = new Point();
		overrideGetSize(disp, windowSize);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		this.canvas = canvas;
		for (int y = 0; y < MoleController.HOLE_COLUMN; y++) {
			for (int x = 0; x < MoleController.HOLE_ROW; x++) {
				adjustImageSize(x, y);
				setHoleArea(x, y);
				drawHole(x, y);
			}
		}
	}
	
	private void adjustImageSize(int x, int y) {
		int holeNum = calcHoleNumber(x, y);
		if(windowSize.x / MoleController.HOLE_COLUMN < mBitmap[holeNum].getWidth()) {
			final int resizedWidth = windowSize.x / (MoleController.HOLE_COLUMN+1) - 10;
			mBitmap[holeNum] = Bitmap.createScaledBitmap(mBitmap[holeNum], 
					resizedWidth, 
					mBitmap[holeNum].getHeight() * resizedWidth / mBitmap[holeNum].getWidth(), 
					true);
		}
	}

	private void setHoleArea(int x, int y) {
		int holeNum = calcHoleNumber(x, y);
		if(holeArea[holeNum] == null) {
			holeArea[holeNum] = new HoleArea();
		}
		holeArea[holeNum].setImageArea(windowSize.x / (MoleController.HOLE_ROW+1) * (x+1),
				windowSize.y / (MoleController.HOLE_COLUMN+1) * (y+1), 
				mBitmap[holeNum]);
	}
	
	private void drawHole(int x, int y) {
		int holeNum = calcHoleNumber(x, y);
		canvas.drawBitmap(mBitmap[holeNum],
				holeArea[holeNum].left,
				holeArea[holeNum].top,
				mPaint[holeNum]);
	}

	private int calcHoleNumber(int x, int y) {
		int holeNum = x * MoleController.HOLE_COLUMN + y;
		return holeNum;
	}
	
	public boolean isExisted(float x, float y) {
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	public int getTouchedHoleNum(float x, float y) {
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return i;
			}
		}
		// TODO: error処理
		return 0;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		MoleController moles = (MoleController) o;
		for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
			if(moles.getMole(i) == null) {
				mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
			} else if (moles.getMole(i).getType() == EnumMoleType.MIDDLE) {
				mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.mole1);
			} else if(moles.getMole(i).getType() == EnumMoleType.HIGH) {
				mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.mole2);
			} else if(moles.getMole(i).getType() == EnumMoleType.MINUS) {
				mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.mole3);
			}
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

	public void popGotPoint(int holeNum, Mole mole) {
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
