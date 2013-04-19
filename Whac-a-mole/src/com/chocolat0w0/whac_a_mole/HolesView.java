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
import android.view.View;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class HolesView extends View implements Observer {
	
	private Point windowSize = null;
	private Bitmap[] mBitmap = new Bitmap[MolesController.HOLE_NUMBER];
	
	private List<HoleImage> mHoleImage = new ArrayList<HoleImage>(MolesController.HOLE_NUMBER);
	
	public HolesView(Context context, MolesController molesController,
			List<HoleImage> holeImages) {
		super(context);
		this.mHoleImage = holeImages;
		WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display disp = wm.getDefaultDisplay();
		windowSize = new Point();
		overrideGetSize(disp, windowSize);
		for (int i = 0; i < MolesController.HOLE_NUMBER; i++) {
			mBitmap[i] = BitmapFactory.decodeResource(getResources(), R.drawable.hole);
			mHoleImage.add(new HoleImage(molesController.getMoleAt(i), this, windowSize));
		}
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		for(HoleImage h : mHoleImage) {
			h.setHoleArea(windowSize);
			h.draw(canvas);
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		MolesController molesController = (MolesController) o;
		int holeNum = 0;
		for(HoleImage h : mHoleImage) {
			h.changeMole(molesController.getMoleAt(holeNum), this, windowSize);
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
