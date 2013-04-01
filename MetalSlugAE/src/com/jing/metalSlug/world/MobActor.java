package com.jing.metalSlug.world;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

public class MobActor extends Entity {
	protected Weapon cWeapon = null;
	protected int dir = 0;
	protected final int DOWN = 3;

	protected boolean isPassive = false;
	protected boolean isHurt = false;

	protected boolean isLive = true;
	protected boolean isMoving = false;

	protected boolean isActing = false;

	protected final int LEFT = 1;
	int baseHeight = 0;
	int baseWidth = 0;

	public MobActor(float x, float y, World world) {
		super(x, y, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Rectangle getRec() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		// TODO Auto-generated method stub

	}

}
