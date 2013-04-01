package com.jing.metalSlug.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public abstract class Entity {

	protected float x, y, w, h;
	protected float px,py,pw,ph;
	protected boolean isPhysicalExist = false;
	
	public boolean isPhysicalExist() {
		return isPhysicalExist;
	}

	public void setPhysicalExist(boolean isPhysicalExist) {
		this.isPhysicalExist = isPhysicalExist;
	}

	//inject scene later on;
	World world = null;
	
	public Entity(float x, float y,World world) {
		super();
		this.x = x;
		this.y = y;
		this.world = world;
	}
	
	public abstract Rectangle getRec();
	
	public abstract void update(GameContainer gc, int delta);

	public abstract void render(GameContainer gc, Graphics g);

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
