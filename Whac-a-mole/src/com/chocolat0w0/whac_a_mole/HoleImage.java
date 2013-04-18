package com.chocolat0w0.whac_a_mole;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;


class HoleImage {

	private IMole mole = null;
	private Bitmap image = null;
	private float left = 0;
	private float right = 0;
	private float top = 0;
	private float bottom = 0;
	private Paint mPaint = null;
	
	public HoleImage(IMole mole, HolesView holesView, Point windowSize) {
		this.mole = mole;
		this.image = BitmapFactory.decodeResource(holesView.getResources(), getImageId());
		adjustImageSize(windowSize.x);
		mPaint = new Paint();
	}
	
	public void changeMole(IMole mole, HolesView holesView, Point windowSize) {
		this.mole = mole;
		this.image = BitmapFactory.decodeResource(holesView.getResources(), getImageId());
		adjustImageSize(windowSize.x);
	}
	
	int getImageId() {
		if(mole == null) {
			return R.drawable.hole;
		}
		switch(mole.getType()) {
		case MIDDLE :
			return R.drawable.mole1;
		case HIGH :
			return R.drawable.mole2;
		case MINUS :
			return R.drawable.mole3;
		case NULL :
			return R.drawable.hole;
		default :
			break;
		}

		return R.drawable.hole;
	}

	public Bitmap getImage() {
		return image;
	}
	
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image,
				getLeft(),
				getTop(),
				mPaint);
	}
	
	private void adjustImageSize(int windowSizeX) {
		if(windowSizeX / MolesController.HOLE_COLUMN < image.getWidth()) {
			final int resizedWidth = windowSizeX / (MolesController.HOLE_COLUMN+1) - 10;
			image = Bitmap.createScaledBitmap(image, 
					resizedWidth, 
					image.getHeight() * resizedWidth / image.getWidth(), 
					true);
		}
	}
	
	public void setHoleArea(int x, int y, Point windowSize) {
		setImageArea(windowSize.x / (MolesController.HOLE_ROW+1) * (x+1),
				windowSize.y / (MolesController.HOLE_COLUMN+1) * (y+1));
	}
	
	public void setImageArea(int centerX, int centerY) {
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

	public float getLeft() {
		return left;
	}

	public float getRight() {
		return right;
	}
	
	public float getTop() {
		return top;
	}
	
	public float getBottom() {
		return bottom;
	}

}
