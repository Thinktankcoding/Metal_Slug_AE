package com.jing.metalSlug.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.newdawn.slick.geom.Vector2f;

import com.jing.metalSlug.main.StaticValues;
import com.jing.metalSlug.world.Actor;
import com.jing.metalSlug.world.AnimationWrapper;
import com.jing.metalSlug.world.ControllableActor;
import com.jing.metalSlug.world.World;

public class ActorFactory {

	public static Actor createActor(String roleName, boolean controllable,
			int x, int y, World world) {
		Actor actor = null;
		Vector2f WH = StaticValues.getWH(roleName);
		if (controllable) {
			actor = new ControllableActor(x, y, (int) WH.getX(),
					(int) WH.getY(), world);
		} else {
			actor = new Actor(x, y, (int) WH.getX(), (int) WH.getY(), world);
		}
		HashMap<String, AnimationWrapper> states = StaticValues
				.getRoleAnimations(roleName);

		actor.setStates(states);

		return actor;

	}

}
