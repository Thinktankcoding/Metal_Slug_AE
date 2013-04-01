package com.jing.metalSlug.world;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import com.jing.metalSlug.main.GameConstants;

import static com.jing.metalSlug.main.GameConstants.*;

public class Actor extends Entity {

	int baseHeight = 0;
	int baseWidth = 0;
	Map<Integer, Weapon> weapons = new HashMap<Integer, Weapon>();
	// current Weapon
	protected Weapon cWeapon = null;
	protected int dir = 0;
	protected final int DOWN = 3;

	protected boolean isPassive = false;
	protected boolean isHurt = false;

	protected boolean isLive = true;
	protected boolean isMoving = false;

	protected boolean isActing = false;

	protected final int LEFT = 1;
	protected int movingDir;

	String name = null;
	protected final int RIGHT = 0;
	protected float speed;
	HashMap<String, AnimationWrapper> states = null;
	protected final int UP = 2;

	AnimationWrapper upperAnimation = null;
	AnimationWrapper lowerAnimation = null;
	
	public Actor(float x, float y, int baseWidth, int baseHeight, World world) {
		super(x, y, world);
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
		isPhysicalExist = true;

		speed = 0.1f;
	}

	protected boolean collidedWithObject() {
		List<Entity> entities = world.getEntities();
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) != this) {
				if (entities.get(i).isPhysicalExist
						&& entities.get(i).getRec().intersects(this.getRec())) {
					return true;
				}

			}
		}
		return false;
	}

	public void fire() {
		if (!isPassive) {
			if (cWeapon.needReload()) {
				if (!isActing) {
					isActing = true;
					cWeapon.reload();
					upperAnimation = getAnimationWrapper(RELOAD_UPPER, dir);
				}
			} else {
				cWeapon.trigger();
				if (!isActing) {
					isActing = true;
					upperAnimation = getAnimationWrapper(PISTOL_UPPER, dir);
				}
			}
		}
	}

	public void reload() {

	}

	protected AnimationWrapper getAnimationWrapper(String prefix, int dir) {
		AnimationWrapper wrapper = null;
		if (dir == LEFT) {
			wrapper = states.get(prefix + "_left");
		} else {
			wrapper = states.get(prefix + "_right");
		}
		return wrapper;
	}

	public Rectangle getRec() {
		Rectangle area = new Rectangle(x, y - baseHeight / 3, baseWidth,
				baseHeight / 3);
		return area;
	}

	public void hit() {

	}

	public void addWeapon(Weapon w) {
		w.setActor(this);
		cWeapon = w;
	}

	public void idle() {
		if (!isPassive) {
			if (!isActing) {
				upperAnimation = getAnimationWrapper(IDLE_UPPER, dir);
			}
			lowerAnimation = getAnimationWrapper(IDLE_LOWER, dir);
		}

	}

	public void move(int d) {
		if (!isPassive) {
			py = y;
			px = x;
			switch (d) {
			case UP:
				y -= speed;
				break;
			case DOWN:
				y += speed;
				break;
			case LEFT:
				if (dir == RIGHT) {
					if (isActing)
						return;
					dir = LEFT;
				}
				x -= speed;
				break;
			case RIGHT:
				if (dir == LEFT) {
					if (isActing)
						return;
					dir = RIGHT;
				}
				x += speed;
				break;
			}
			if (collidedWithObject()) {
				y = py;
				x = px;
			}

			if (!isActing) {
				upperAnimation = getAnimationWrapper(RUN_UPPER, dir);
			}
			lowerAnimation = getAnimationWrapper(RUN_LOWER, dir);
		}

	}

	@Override
	public void render(GameContainer gc, Graphics g) {
		if (dir == RIGHT) {
			if (lowerAnimation != null) {
				g.drawAnimation(lowerAnimation, x, y
						- lowerAnimation.getCurrentFrame().getHeight());
			}
			if (upperAnimation != null) {
				g.drawAnimation(
						upperAnimation,
						x + lowerAnimation.getAdjustX(),
						y - upperAnimation.getCurrentFrame().getHeight()
								+ lowerAnimation.getAdjustY()
								+ upperAnimation.getAdjustY());
			}
		} else {
			if (lowerAnimation != null) {
				Image image = lowerAnimation.getCurrentFrame();
				g.drawAnimation(lowerAnimation,
						x + baseWidth - image.getWidth(), y
								- lowerAnimation.getCurrentFrame().getHeight());
			}
			if (upperAnimation != null) {
				Image image = upperAnimation.getCurrentFrame();
				g.drawAnimation(
						upperAnimation,
						x + baseWidth - image.getWidth()
								+ lowerAnimation.getAdjustX(),
						y - upperAnimation.getCurrentFrame().getHeight()
								+ lowerAnimation.getAdjustY()
								+ upperAnimation.getAdjustY());
			}
		}
	}

	public void setStates(HashMap<String, AnimationWrapper> animations) {
		this.states = animations;
		dir = RIGHT;
		upperAnimation = getAnimationWrapper(IDLE_UPPER, dir);
		lowerAnimation = getAnimationWrapper(IDLE_LOWER, dir);
	}

	@Override
	public void update(GameContainer gc, int delta) {
		// finish fire
		if (isActing) {
			if (upperAnimation != null) {
				if (upperAnimation.isStopped()) {
					isActing = false;
					upperAnimation.restart();
				}
			} else {
				if (lowerAnimation.isStopped()) {
					isActing = false;
					lowerAnimation.restart();
				}
			}
		}
	}

}
