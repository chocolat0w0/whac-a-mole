package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

class HolesController {
	
	private HolesView mHolesView;
	private Holes mHoles;
	private TotalPoint mPoint;
	private HoleArea[] holeArea = new HoleArea[Holes.HOLE_NUMBER];
	private HoleImage[] holeImage = new HoleImage[Holes.HOLE_NUMBER];

	public HolesController(Context context, Holes holes, TotalPoint point) {
		mHolesView = new HolesView(context, holes, holeArea);
		this.mHoles = holes;
		this.mPoint = point;
		holes.addObserver(mHolesView);
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
		int point = mHoles.getTouchedMolePoint(touchedHoleNum);
		mPoint.add(point);
		mHoles.touch(touchedHoleNum);
		
	}

	
	public boolean isExisted(float x, float y) {
		
		for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return true;
			}
		}
		return false;
	}
	
	private int getTouchedHoleNum(float x, float y) {
		for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
			if(holeArea[i].isContain(x, y)) {
				return i;
			}
		}
		// TODO: error処理
		return -1;
	}
	
}
