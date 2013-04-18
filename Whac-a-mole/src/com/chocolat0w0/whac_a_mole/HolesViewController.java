package com.chocolat0w0.whac_a_mole;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

class HolesViewController {
	
	private HolesView mHolesView;
	private MolesController mMolesCtr;
	private TotalPoint mPoint;
	private List<HoleImage> mHoleImages = new ArrayList<HoleImage>(MolesController.HOLE_NUMBER);

	public HolesViewController(Context context, MolesController molesController, TotalPoint point) {
		mHolesView = new HolesView(context, molesController, mHoleImages);
		this.mMolesCtr = molesController;
		this.mPoint = point;
		molesController.addObserver(mHolesView);
	}

	public void addViewGroup(RelativeLayout viewGroup) {
		viewGroup.addView(mHolesView);
	}

	public void refresh() {
		mHolesView.invalidate();
	}

	public void touch(MotionEvent event) {
		if(!isExisted(event.getX(), event.getY() - 100)) {
			return;
		}
		int touchedHoleNum = getTouchedHoleNum(event.getX(), event.getY() - 100);
		int point = mMolesCtr.getTouchedMolePoint(touchedHoleNum);
		mPoint.add(point);
		mMolesCtr.touch(touchedHoleNum);
		
	}

	
	public boolean isExisted(float x, float y) {
		
		for(HoleImage h : mHoleImages) {
			if(h.isContain(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	private int getTouchedHoleNum(float x, float y) {
		int holeNum = 0;
		for(HoleImage h : mHoleImages) {
			if(h.isContain(x, y)) {
				return holeNum;
			}
			holeNum++;
		}
		
		// TODO: error処理
		return -1;
	}
	
}
