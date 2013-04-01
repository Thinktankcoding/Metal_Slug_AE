package com.jing.metalSlug.world;

import static com.jing.metalSlug.main.GameConstants.PISTOL_UPPER;

public class Weapon {

	private Actor actor;

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	private int type;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private int inteval;

	private int currentLoad = 1;
	private int maxLoad = 0;
	private int inventoryBullets = 0;

	public static final int METEEL = 0;
	public static final int NORMAL = 1;
	public static final int AUTOMATIC = 2;
	private long lastTriggerTime = 0;

	public Weapon(int type, int maxLoad) {
		this.type = type;
		this.actor = actor;
		if (type != METEEL) {
			this.maxLoad = maxLoad;
			currentLoad = maxLoad;
		}
	}

	public void setInterval(int time) {
		inteval = time;
	}

	public void reload() {
		currentLoad = maxLoad;
		inventoryBullets -= maxLoad;
	}

	public boolean needReload() {
		return currentLoad <= 0;
	}

	public boolean trigger() {
		if (type == AUTOMATIC) {
			long currentTime = System.currentTimeMillis();
			long timeGap = currentTime - lastTriggerTime;
			if (timeGap > inteval) {

			}
		} else if (type == NORMAL) {
			if (!actor.isActing) {
				currentLoad--;
				if (currentLoad <= 0)
					return false;
			} else {
				return false;
			}
		} else if (type == METEEL) {

		}
		return true;
	}

}
