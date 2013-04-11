package com.chocolat0w0.whac_a_mole;

import android.graphics.Bitmap;

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
		if (left <= x && x <= right && top <= y && y <= getBottom() ) {
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
