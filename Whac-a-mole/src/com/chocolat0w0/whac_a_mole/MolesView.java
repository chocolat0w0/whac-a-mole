package com.chocolat0w0.whac_a_mole;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class MolesView extends View implements Observer {
	
	private Point windowSize = null;
	private Bitmap[] mBitmap = new Bitmap[Moles.HOLE_NUMBER];
	
	private List<MoleImage> mMoleImage = new ArrayList<MoleImage>(Moles.HOLE_NUMBER);
	private Moles mMoles = null;
	private TotalPoint mTotalPoint;
	
	public MolesView(Context context, Moles moles, TotalPoint totalPoint) {
		super(context);
		this.mMoles = moles;
		this.mTotalPoint = totalPoint;
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		windowSize = new Point();
		overrideGetSize(disp, windowSize);
		for (int i = 0; i < Moles.HOLE_NUMBER; i++) {
			mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
			mMoleImage.add(new MoleImage(moles.getMoleAt(i), this, windowSize));
		}
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for(MoleImage h : mMoleImage) {
			h.setHoleArea(windowSize);
			h.draw(canvas);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Moles moles = (Moles) o;
		int holeNum = 0;
		for(MoleImage h : mMoleImage) {
			h.changeMole(moles.getMoleAt(holeNum), this, windowSize);
			holeNum++;
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
	
	void touch(MotionEvent event) {
		if(!isExisted(event.getX(), event.getY())) {
			return;
		}
		int touchedHoleNum = getTouchedHoleNum(event.getX(), event.getY());
		int point = mMoles.getTouchedMolePoint(touchedHoleNum);
		mTotalPoint.add(point);
		mMoles.touch(touchedHoleNum);
		
	}
	
	private boolean isExisted(float x, float y) {
		for(MoleImage h : mMoleImage) {
			if(h.isContain(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	private int getTouchedHoleNum(float x, float y) {
		int holeNum = 0;
		for(MoleImage h : mMoleImage) {
			if(h.isContain(x, y)) {
				return holeNum;
			}
			holeNum++;
		}
		
		// TODO: error処理
		return -1;
	}
	
	


//	public void popGotPoint(int holeNum, IMole mole) {
//		// TODO 子viewをもう一枚つくって操作する？
//		Bitmap image = null;
//		switch(mole.getType()) {
//		case MIDDLE:
//			image = BitmapFactory.decodeResource(getResources(), R.drawable.p300);
//			break;
//		case HIGH:
//			image = BitmapFactory.decodeResource(getResources(), R.drawable.p500);
//			break;
//		case MINUS:
//			image = BitmapFactory.decodeResource(getResources(), R.drawable.m600);
//			break;
//		default:
//			image = BitmapFactory.decodeResource(getResources(), R.drawable.p300);
//			break;
//		}
//		
//		 canvas.drawBitmap(image,
//				holeImage[holeNum].getLeft(),
//				holeImage[holeNum].getTop(),
//				mPaint[holeNum]);
//	}

}
