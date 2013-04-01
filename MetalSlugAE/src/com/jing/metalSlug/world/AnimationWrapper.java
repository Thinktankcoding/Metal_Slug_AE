package com.jing.metalSlug.world;

import org.newdawn.slick.Animation;

public class AnimationWrapper extends Animation{
	
	private float adjustX;
	private float adjustY;
	private boolean isInterruptable = true;
	
	private Animation animation;
	Entity entity = null;

	public AnimationWrapper(Entity entity,float adjustX, float adjustY) {
		super();
		this.entity = entity;
		this.animation = animation;
		this.adjustY = adjustY;
		this.adjustX = adjustX;
		
	}
	public float getAdjustX() {
		return adjustX;
	}

	public float getAdjustY() {
		return adjustY;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setAdjustX(float adjustX) {
		this.adjustX = adjustX;
	}

	public void setAdjustY(float adjustY) {
		this.adjustY = adjustY;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
}
