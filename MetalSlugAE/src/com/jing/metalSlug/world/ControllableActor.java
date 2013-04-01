package com.jing.metalSlug.world;

import static com.jing.metalSlug.main.GameConstants.PISTOL_UPPER;
import static com.jing.metalSlug.main.GameConstants.RUN_UPPER;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.jing.metalSlug.main.GameConstants;

public class ControllableActor extends Actor {

	public ControllableActor(float x, float y, int width, int height,
			World world) {
		super(x, y, width, height, world);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(GameContainer gc, int delta) {
		super.update(gc, delta);
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_S)
				|| input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D)) {
			if (input.isKeyDown(Input.KEY_W)) {
				move(UP);
			}
			if (input.isKeyDown(Input.KEY_S)) {
				move(DOWN);
			}
			if (input.isKeyDown(Input.KEY_A)) {
				move(LEFT);
			}
			if (input.isKeyDown(Input.KEY_D)) {
				move(RIGHT);
			}
		} else {
			idle();

		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			fire();
		}
	}
}
